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
  val packageName = "dev.teogor.ceres.plugin.info"

  @Internal
  var resultDirectory: File = project.file("${project.buildDir}/generated/ceres/src/main/kotlin/")

  @Internal
  val outputFilePath: File = project.file(
    "${resultDirectory}/${packageName.replace(".", "/")}/",
  )

  @TaskAction
  fun action() {
    if (!resultDirectory.exists()) {
      resultDirectory.mkdirs() // verify output exists
    }

    val result = createLibraryProcessor(
      githubBranchName = "main",
    ).gatherDependencies()

    // write to disk
    result.writeToDisk(outputFilePath)
  }

  companion object {
    private val LOGGER = LoggerFactory.getLogger(AboutLibrariesTask::class.java)
  }
}
