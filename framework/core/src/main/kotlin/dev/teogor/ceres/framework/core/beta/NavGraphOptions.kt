package dev.teogor.ceres.framework.core.beta

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.CeresAppState

data class NavGraphOptions(
  val windowSizeClass: WindowSizeClass,
  val ceresAppState: CeresAppState,
  val baseActions: BaseActions,
  val padding: PaddingValues,
)
