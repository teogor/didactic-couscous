package dev.teogor.ceres.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

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
          subprojects.forEach {
            dependsOn("${it.path}:publish")
          }
        }
        println("$path:$taskName")
      }
    }
  }
}
