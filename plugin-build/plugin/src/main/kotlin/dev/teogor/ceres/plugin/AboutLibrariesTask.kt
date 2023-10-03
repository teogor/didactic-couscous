package dev.teogor.ceres.plugin

import dev.teogor.ceres.plugin.model.writeToDisk
import java.io.File
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.slf4j.LoggerFactory

@CacheableTask
abstract class AboutLibrariesTask : BaseAboutLibrariesTask() {
  @Input
  val outputFileName = "CeresInfo.kt"

  @Internal
  var resultDirectory: File = project.file("${project.buildDir}/generated/ceres/src/main/kotlin/dev/teogor/ceres/plugin/info/")
    set(value) {
      field = value
      combinedLibrariesOutputFile = File(resultDirectory, outputFileName)
    }

  private var combinedLibrariesOutputFile = File(resultDirectory, outputFileName)

  @TaskAction
  fun action() {
    if (!resultDirectory.exists()) {
      resultDirectory.mkdirs() // verify output exists
    }

    val result = createLibraryProcessor(
      githubBranchName = "main",
    ).gatherDependencies()

    LOGGER.debug("combinedLibrariesOutputFile={}", combinedLibrariesOutputFile)
    // write to disk
    result.writeToDisk(combinedLibrariesOutputFile)
  }

  companion object {
    private val LOGGER = LoggerFactory.getLogger(AboutLibrariesTask::class.java)
  }
}
