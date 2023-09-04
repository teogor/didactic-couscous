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

@Composable
fun MenuConfig.applyMenuConfig() = apply {
  header = {
    MenuHeader()
  }
  menuSheet = {
    MenuSheet(this)
  }
}


@Composable
private fun MenuHeader() = MenuTitle(
  title = "Ceres",
)

private fun MenuSheet(
  menuScope: MenuScope,
) = menuScope.menu {
  menuTop {
    menuUserData(
      clickable = {
        // navigateTo(UserPrefRoute)
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
        // navigateTo(SettingsRoute)
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
        // navigateTo(AboutScreenRoute)
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
