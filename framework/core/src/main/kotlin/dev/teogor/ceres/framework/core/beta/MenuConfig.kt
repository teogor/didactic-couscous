package dev.teogor.ceres.framework.core.beta

import androidx.compose.runtime.Composable
import dev.teogor.ceres.framework.core.deprecated.menu.MenuScope

class MenuConfig {
  var header: (@Composable () -> Unit)? = null
  var menuSheet: (MenuScope.() -> Unit)? = null
}
