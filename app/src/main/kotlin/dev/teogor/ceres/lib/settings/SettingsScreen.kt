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

package dev.teogor.ceres.lib.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material.icons.filled.Style
import androidx.compose.runtime.Composable
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.navigateTo
import dev.teogor.ceres.framework.core.app.setScreenInfo
import dev.teogor.ceres.framework.core.screen.floatingButton
import dev.teogor.ceres.framework.core.screen.isStatusBarVisible
import dev.teogor.ceres.framework.core.screen.isVisible
import dev.teogor.ceres.framework.core.screen.showBackButton
import dev.teogor.ceres.framework.core.screen.showNavBar
import dev.teogor.ceres.framework.core.screen.showSettingsButton
import dev.teogor.ceres.framework.core.screen.toolbarTitle
import dev.teogor.ceres.framework.core.screen.toolbarTokens
import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.core.LazyColumnScreen

@Composable
internal fun SettingsRoute(
  baseActions: BaseActions,
) {
  baseActions.setScreenInfo {
    showNavBar {
      false
    }
    isStatusBarVisible {
      true
    }
    toolbarTokens {
      toolbarTitle {
        "Settings"
      }
      showSettingsButton {
        false
      }
      showBackButton {
        true
      }
    }

    floatingButton {
      isVisible {
        false
      }
    }
  }

  SettingsScreen(
    clickable = { destination ->
      baseActions.navigateTo(destination)
    },
    developerModeEnabled = false, // developerModePreferences().enabled,
  )
}

@Composable
fun SettingsScreen(
  clickable: (ScreenRoute) -> Unit,
  developerModeEnabled: Boolean,
) = SettingsScreenConfig {
  settingsItem(
    title = "Look & Feel",
    description = "Design & color options",
    icon = Icons.Default.Style,
    clickable = {
      // clickable(LookRoute)
    },
  )
  settingsItem(
    title = "Notification",
    description = "Customize the notification style",
    icon = Icons.Default.Notifications,
    clickable = {
      // clickable(NotificationsRoute)
    },
  )
  settingsItem(
    title = "Other",
    description = "Advanced testing features",
  )
  settingsItem(
    title = "Backup & Restore",
    description = "Backup and restore your settings, contributors, artists",
    icon = Icons.Default.SettingsBackupRestore,
    clickable = {
      // clickable(BackupRoute)
    },
  )
  if (developerModeEnabled) {
    settingsItem(
      title = "Developer options",
      icon = Icons.Default.DeveloperMode,
      clickable = {
        // clickable(DeveloperOptionsRoute)
      },
    )
  }
}

@Composable
fun SettingsScreenConfig(
  block: MutableList<SettingsScreenItem>.() -> Unit,
) = LazyColumnScreen(
  screenName = SettingsScreenRoute.toScreenName(),
  hasScrollbarBackground = false,
) {
  settingsItems(block)
}
