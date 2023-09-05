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

package dev.teogor.ceres.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material.icons.filled.Style
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.setScreenInfo
import dev.teogor.ceres.framework.core.screen.floatingButton
import dev.teogor.ceres.framework.core.screen.isStatusBarVisible
import dev.teogor.ceres.framework.core.screen.isVisible
import dev.teogor.ceres.framework.core.screen.showNavBar
import dev.teogor.ceres.framework.core.screen.showSettingsButton
import dev.teogor.ceres.framework.core.screen.toolbarTitle
import dev.teogor.ceres.framework.core.screen.toolbarTokens
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.builder.compose.Layout
import dev.teogor.ceres.screen.builder.header
import dev.teogor.ceres.screen.builder.item
import dev.teogor.ceres.screen.builder.segmentedButtons
import dev.teogor.ceres.screen.core.ColumnScreen
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

// todo better way to configure this. perhaps use kotlin builder syntax
@Composable
internal fun HomeRoute(
  baseActions: BaseActions,
) {
  baseActions.setScreenInfo {
    showNavBar {
      true
    }

    isStatusBarVisible {
      true
    }

    toolbarTokens {
      isVisible {
        true
      }

      toolbarTitle {
        "Home"
      }

      showSettingsButton {
        true
      }
    }

    floatingButton {
      isVisible {
        false
      }
    }
  }

  SettingsLayout()
  // ClockConfigScreen()
  // HomeScreen()
}

@Composable
fun HomeScreen() = ColumnScreen(
  hasScrollbarBackground = false,
  screenName = HomeScreenConfig.toScreenName(),
) {
  repeat(100) {
    Text(
      modifier = Modifier.padding(
        horizontal = 16.dp,
        vertical = 14.dp,
      ),
      text = "here we go as $it",
    )
  }
}

@Composable
private fun ClockConfigScreen(
) = Layout(
  screenName = HomeScreenConfig,
) {
  header {
    "Time Interval Options"
  }

  item(
    title = "Timer Duration",
    subtitle = "Adjust the duration of the timer",
    subtitleColor = ColorSchemeKeyTokens.Error,
  ) {
    segmentedButtons(
      options = listOf(
        "2 MIN",
        "5 MIN",
        "10 MIN",
        "20 MIN",
        "30 MIN",
      ),
      selectedOption = 1,
    )
  }

}

@Composable
private fun SettingsLayout(
) = Layout(
  screenName = HomeScreenConfig,
) {

  header {
    "UI"
  }

  item(
    title = "Look & Feel",
    subtitle = "Design & color options",
    imageVector = Icons.Default.Style,
    clickable = {

    },
  )

  header {
    "System"
  }

  item(
    title = "Notification",
    subtitle = "Customize the notification style",
    imageVector = Icons.Default.Notifications,
    clickable = {

    },
  )

  item(
    title = "Backup & Restore",
    subtitle = "Full backup of your app",
    imageVector = Icons.Default.SettingsBackupRestore,
    clickable = {

    },
  )

  item(
    title = "Backup & Restore",
    subtitle = "Full backup of your app",
    clickable = {

    },
  )

  item(
    title = "Backup & Restore",
    subtitle = "Full backup of your app",
    imageVector = Icons.Default.SettingsBackupRestore,
    clickable = {

    },
  )

}
