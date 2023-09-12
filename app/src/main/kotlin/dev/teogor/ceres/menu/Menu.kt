/*
 * Copyright 2023 teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.ceres.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import dev.teogor.ceres.framework.core.menu.MenuTitle
import dev.teogor.ceres.framework.core.menu.menu
import dev.teogor.ceres.framework.core.menu.menuDivider
import dev.teogor.ceres.framework.core.menu.menuItem
import dev.teogor.ceres.framework.core.menu.menuTop
import dev.teogor.ceres.framework.core.menu.menuUserData
import dev.teogor.ceres.framework.core.model.MenuConfig
import dev.teogor.ceres.navigation.core.LocalNavigationParameters
import dev.teogor.ceres.navigation.core.ScreenRoute

@Composable
fun MenuConfig.applyMenuConfig() = apply {
  val navigationParameters = LocalNavigationParameters.current

  fun ScreenRoute.navigateTo() {
    navigationParameters.screenRoute = this
  }

  headerContent = {
    MenuTitle(
      title = "Ceres",
    )
  }

  menuContent = {
    // menu configurator
    menu {
      // menu header
      menuTop {
        // ceres built-in user details
        menuUserData()

        // use menu divider to add a divider
        menuDivider()

        // demo showcase of menu top header elements
        repeat(3) {
          menuItem(
            content = "Element $it",
            icon = Icons.Outlined.Settings,
          )
        }
      }

      // menuContent {
      //   menuItem(
      //     content = "Settings",
      //     icon = Icons.Outlined.Settings,
      //     clickable = {
      //       SettingsScreenRoute.navigateTo()
      //     },
      //   )
      //
      //   menuItem(
      //     content = "Help and feedback",
      //     icon = Icons.Outlined.HelpOutline,
      //   )
      //
      //   menuItem(
      //     content = "Privacy Policy",
      //     icon = Icons.Outlined.Link,
      //   )
      //
      //   menuItem(
      //     content = "Terms of service",
      //     icon = Icons.Outlined.Link,
      //   )
      //
      //   menuItem(
      //     content = "About",
      //     icon = Icons.Outlined.Details,
      //   )
      //
      //   menuDivider()
      //
      //   menuFooter(
      //     licenseHolder = "teogor (Teodor G.)",
      //     modifier = Modifier
      //       .padding(horizontal = 6.dp)
      //       .padding(top = 10.dp, bottom = 4.dp),
      //   )
      //
      //   menuUserId(
      //     modifier = Modifier
      //       .padding(horizontal = 6.dp)
      //       .padding(top = 2.dp),
      //   )
      // }
    }
  }
}
