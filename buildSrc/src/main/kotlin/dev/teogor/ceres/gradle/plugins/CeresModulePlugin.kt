package dev.teogor.ceres.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.nio.file.Files

class CeresModulePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      // Create an extension to allow users to configure the "ceresModule" block
      val ceresModuleExtension = project.extensions.create(
        "ceresModule", CeresModuleExtension::class.java
      )
      pluginManager.apply("com.vanniktech.maven.publish")

      // Configure the "ceresModule" block based on the extension properties
      project.afterEvaluate {
        val groupId = ceresModuleExtension.groupId
        val artifactIdPrefix = ceresModuleExtension.artifactIdPrefix!!
        val version = ceresModuleExtension.version!!

        println("$artifactIdPrefix Modules (v$version)")
        // Access all subprojects within the current module
        subprojects {
          pluginManager.apply("ceres.library.publish")
          val libraryPublish = project.extensions.getByType(CeresLibraryExtension::class.java)

          val moduleName = this.name

          libraryPublish.apply {
            this.artifactId = "$artifactIdPrefix-$moduleName"
            this.version = version
          }
          println("$groupId:$artifactIdPrefix-$moduleName:$version")
        }
        val artifactIdPrefixTitlecase = artifactIdPrefix.replaceFirstChar { it.titlecase() }
        val taskName = "publish${artifactIdPrefixTitlecase}LibrariesToMavenCentral"
        project.tasks.create(taskName) {
          subprojects {
            dependsOn("$path:publish")
          }
        }

        prepareWorkflow(
          outputFile = rootProject.file(".github/workflows/publish-$name.yml"),
          path = name,
          artifactIdPrefix = artifactIdPrefix,
          artifactIdPrefixTitlecase = artifactIdPrefixTitlecase,
        )
      }
    }
  }

  private fun prepareWorkflow(
    outputFile: File,
    path: String,
    artifactIdPrefix: String,
    artifactIdPrefixTitlecase: String,
  ) {
    val dollar = "$"
    val workflowContent = """
        name: Publish ${artifactIdPrefix.uppercase()}

        on:
          workflow_dispatch:

        jobs:
          publish:
            name: Snapshot build and publish
            runs-on: ubuntu-latest
            environment: PRODUCTION
            timeout-minutes: 120
            env:
              ORG_GRADLE_PROJECT_mavenCentralUsername: $dollar{{ secrets.OSSRH_USERNAME }}
              ORG_GRADLE_PROJECT_mavenCentralPassword: $dollar{{ secrets.OSSRH_PASSWORD }}
              ORG_GRADLE_PROJECT_signingInMemoryKeyId: $dollar{{ secrets.SIGNING_KEY_ID }}
              ORG_GRADLE_PROJECT_signingInMemoryKeyPassword: $dollar{{ secrets.SIGNING_PASSWORD }}
              ORG_GRADLE_PROJECT_signingInMemoryKey: $dollar{{ secrets.SIGNING_KEY }}
              SONATYPE_CONNECT_TIMEOUT_SECONDS: 120
              SONATYPE_CLOSE_TIMEOUT_SECONDS: 1800

            steps:
              - name: Check out code
                uses: actions/checkout@v3.1.0

              - name: Set up JDK 17
                uses: actions/setup-java@v3.5.1
                with:
                  distribution: 'zulu'
                  java-version: 17

              - name: Grant Permission to Execute Gradle
                run: chmod +x gradlew

              - name: Publish to MavenCentral
                run: |
                  ./gradlew :$path:publish${artifactIdPrefixTitlecase}LibrariesToMavenCentral --no-configuration-cache
    """.trimIndent()

    Files.write(outputFile.toPath(), workflowContent.toByteArray())
  }
}
