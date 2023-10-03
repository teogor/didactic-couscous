package dev.teogor.ceres.plugin

import dev.teogor.ceres.plugin.util.LibrariesProcessor
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.slf4j.LoggerFactory

abstract class BaseAboutLibrariesTask : DefaultTask() {
  private val LOGGER = LoggerFactory.getLogger(BaseAboutLibrariesTask::class.java)!!

  private val rootDir = project.rootDir

  @Internal
  open var variant: String? = null

  protected fun createLibraryProcessor(
    githubBranchName: String? = null
  ) = LibrariesProcessor(
    githubBranchName,
  )
}
