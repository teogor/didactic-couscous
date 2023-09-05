package dev.teogor.ceres.screen.builder.compose

import androidx.compose.runtime.Composable
import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.builder.model.ConfigScreenView
import dev.teogor.ceres.screen.builder.screenItems
import dev.teogor.ceres.screen.core.LazyColumnScreen

@Composable
inline fun Layout(
  screenName: ScreenRoute,
  hasScrollbar: Boolean = true,
  noinline bottomContent: (@Composable () -> Unit)? = null,
  crossinline block: MutableList<ConfigScreenView>.() -> Unit,
) = LazyColumnScreen(
  screenName = screenName.toScreenName(),
  hasScrollbarBackground = false,
  hasScrollbar = hasScrollbar,
  bottomContent = bottomContent,
) {
  screenItems {
    block()
  }
}
