package dev.teogor.ceres.navigation.core.menu

import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.ui.foundation.graphics.Icon

open class TopLevelDestination(
  val selectedIcon: Icon,
  val unselectedIcon: Icon,
  val iconText: String,
  val titleText: String,
  val screenRoute: ScreenRoute? = null,
)
