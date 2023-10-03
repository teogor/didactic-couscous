package dev.teogor.ceres.plugin.util

import dev.teogor.ceres.plugin.model.ResultContainer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LibrariesProcessor(
  private var githubBranchName: String? = null,
) {

  fun gatherDependencies(): ResultContainer {
    LOGGER.info("githubBranchName = $githubBranchName")

    return ResultContainer(
      githubBranchName,
    )
  }

  companion object {
    internal val LOGGER: Logger = LoggerFactory.getLogger(LibrariesProcessor::class.java)
  }
}
