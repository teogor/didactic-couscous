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

package dev.teogor.ceres.lib.lookfeel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material.icons.filled.Style
import androidx.compose.runtime.Composable
import dev.teogor.ceres.feature.home.HomeScreenConfig
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
import dev.teogor.ceres.screen.builder.advancedView
import dev.teogor.ceres.screen.builder.compose.LazyColumnLayout
import dev.teogor.ceres.screen.builder.header
import dev.teogor.ceres.screen.builder.segmentedButtons
import dev.teogor.ceres.screen.builder.simpleView
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens
import kotlin.random.Random

@Composable
internal fun LookAndFeelRoute(
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
        "Look & Feel"
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

  LookAndFeelLayout(
    navigateTo = { destination ->
      baseActions.navigateTo(destination)
    },
  )
}

@Composable
private fun LookAndFeelLayout(
  navigateTo: (ScreenRoute) -> Unit,
) = LazyColumnLayout(
  screenName = HomeScreenConfig,
) {
  header {
    "Appearance"
  }

  advancedView(
    title = "App Theme",
    subtitle = "Change app theme colour",
    icon = Icons.Default.Style,
    clickable = {
    },
  ) {
    val options = listOf("Auto", "White", "Dark")
    segmentedButtons(
      options = options,
      selectedOption = Random.nextInt(options.size),
      onOptionSelected = { option ->

      }
    )
  }

  advancedView(
    title = "Just Black",
    subtitle = "If set on something else then `Off` it will override the App Theme value.",
    subtitleColor = ColorSchemeKeyTokens.Error,
    icon = Icons.Default.Style,
    clickable = {
    },
  ) {
    val options = listOf("Auto", "On", "Off")
    segmentedButtons(
      options = options,
      selectedOption = 2,
      onOptionSelected = { option ->

      }
    )
  }

  advancedView(
    title = "Dynamic Theming",
    icon = Icons.Default.Style,
    clickable = {
    },
  ) {
    
    val options = listOf("Auto", "On", "Off")
    segmentedButtons(
      options = options,
      selectedOption = 2,
      onOptionSelected = { option ->

      }
    )
  }

  header {
    "System"
  }

  simpleView(
    title = "Notification",
    subtitle = "Customize the notification style",
    icon = Icons.Default.Notifications,
    clickable = {
    },
  )

  simpleView(
    title = "Backup & Restore",
    subtitle = "Full backup of your app",
    icon = Icons.Default.SettingsBackupRestore,
    clickable = {
    },
  )

  simpleView(
    title = "Backup & Restore",
    subtitle = "Full backup of your app",
    clickable = {
    },
  )
}
