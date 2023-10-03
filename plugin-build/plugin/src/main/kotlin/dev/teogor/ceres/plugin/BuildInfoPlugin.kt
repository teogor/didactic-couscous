package dev.teogor.ceres.plugin

import dev.teogor.ceres.plugin.util.safeProp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.gradle.util.GradleVersion
import org.slf4j.LoggerFactory

@Suppress("unused") // Public API for Gradle build scripts.
class BuildInfoPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    if (GradleVersion.current() < GradleVersion.version("5.0")) {
      project.logger.error("Gradle 5.0 or greater is required to apply this plugin.")
      return
    }

    project.afterEvaluate {
      // register a global task to generate library definitions
      project.tasks.register<AboutLibrariesTask>("exportBuildInfo") {
        description = "Writes the relevant meta data for the BuildInfo plugin to display dependencies"
        group = "dev.teogor.ceres"
        variant = project.safeProp("aboutLibraries.exportVariant") ?: project.safeProp("exportVariant")
        resultDirectory = project.file(
          project.safeProp("aboutLibraries.exportPath") ?: project.safeProp("exportPath")
          ?: "${project.buildDir}/generated/ceres/"
        )
      }

      project.tasks.register<GenerateConfigFileTask>("generateConfigFile") {

      }
    }
  }

  private val Project.experimentalCache: Boolean
    get() = hasProperty("org.gradle.unsafe.configuration-cache") &&
      property("org.gradle.unsafe.configuration-cache") == "true" ||
      hasProperty("org.gradle.configuration-cache") &&
      property("org.gradle.configuration-cache") == "true"


  companion object {
    private val LOGGER = LoggerFactory.getLogger(BuildInfoPlugin::class.java)
  }
}
