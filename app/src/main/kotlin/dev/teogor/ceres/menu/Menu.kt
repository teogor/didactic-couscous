package dev.teogor.ceres.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Details
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.framework.core.beta.MenuConfig
import dev.teogor.ceres.framework.core.depcreated.menu.MenuScope
import dev.teogor.ceres.framework.core.depcreated.menu.MenuTitle
import dev.teogor.ceres.framework.core.depcreated.menu.menu
import dev.teogor.ceres.framework.core.depcreated.menu.menuContent
import dev.teogor.ceres.framework.core.depcreated.menu.menuDivider
import dev.teogor.ceres.framework.core.depcreated.menu.menuFooter
import dev.teogor.ceres.framework.core.depcreated.menu.menuItem
import dev.teogor.ceres.framework.core.depcreated.menu.menuTop
import dev.teogor.ceres.framework.core.depcreated.menu.menuUserData
import dev.teogor.ceres.framework.core.depcreated.menu.menuUserId
import dev.teogor.ceres.navigation.core.LocalNavigationParameters
import dev.teogor.ceres.navigation.core.ScreenRoute

@Composable
fun MenuConfig.applyMenuConfig() = apply {
  val navigationParameters = LocalNavigationParameters.current

  header = {
    MenuHeader()
  }

  menuSheet = {
    this.menuSheet(
      onNavigation = {
        navigationParameters.screenRoute = it
      }
    )
  }
}


@Composable
private fun MenuHeader() = MenuTitle(
  title = "Ceres",
)

private fun MenuScope.menuSheet(
  onNavigation: (ScreenRoute) -> Unit,
) = menu {
  menuTop {
    menuUserData(
      clickable = {
        //  onNavigation(UserPrefRoute)
      },
    )

    menuDivider()

    menuItem(
      content = "D:: Top Header Item",
      icon = Icons.Outlined.Settings,
    )

    menuItem(
      content = "D:: Middle Header Item",
      icon = Icons.Outlined.Settings,
    )

    menuItem(
      content = "D:: Bottom Header Item",
      icon = Icons.Outlined.Settings,
    )
  }

  menuContent {
    menuItem(
      content = "Settings",
      icon = Icons.Outlined.Settings,
      clickable = {
        // onNavigation(SettingsRoute)
      },
    )

    menuItem(
      content = "Help and feedback",
      icon = Icons.Outlined.HelpOutline,
    )

    menuItem(
      content = "Privacy Policy",
      icon = Icons.Outlined.Link,
    )

    menuItem(
      content = "Terms of service",
      icon = Icons.Outlined.Link,
    )

    menuItem(
      content = "About",
      icon = Icons.Outlined.Details,
      clickable = {
        // onNavigation(AboutScreenRoute)
      },
    )

    menuDivider()

    menuFooter(
      licenseHolder = "teogor (Teodor G.)",
      modifier = Modifier
        .padding(horizontal = 6.dp)
        .padding(top = 10.dp, bottom = 4.dp),
    )

    menuUserId(
      modifier = Modifier
        .padding(horizontal = 6.dp)
        .padding(top = 2.dp),
    )
  }
}
