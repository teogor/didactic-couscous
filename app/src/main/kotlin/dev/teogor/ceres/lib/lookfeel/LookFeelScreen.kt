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
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.teogor.ceres.data.datastore.defaults.AppTheme
import dev.teogor.ceres.data.datastore.defaults.CeresPreferences
import dev.teogor.ceres.data.datastore.defaults.JustBlackTheme
import dev.teogor.ceres.data.datastore.defaults.ceresPreferences
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
import dev.teogor.ceres.screen.builder.switchButton
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

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

  val ceresPreferences = remember {
    ceresPreferences()
  }

  LookAndFeelLayout(
    navigateTo = { destination ->
      baseActions.navigateTo(destination)
    },
    ceresPreferences = ceresPreferences,
  )
}

@Composable
private fun LookAndFeelLayout(
  navigateTo: (ScreenRoute) -> Unit,
  ceresPreferences: CeresPreferences,
) = LazyColumnLayout(
  screenName = HomeScreenConfig,
) {

  header {
    "Appearance"
  }

  advancedView(
    title = "App theme",
    subtitle = "Try another look",
    icon = Icons.Default.Style,
  ) {
    val options = listOf("Auto", "Light", "Dark")
    segmentedButtons(
      options = options,
      selectedOption = when (ceresPreferences.appTheme) {
        AppTheme.FollowSystem -> 0
        AppTheme.ClearlyWhite -> 1
        AppTheme.KindaDark -> 2
      },
      onOptionSelected = { option ->
        val appTheme = when (option) {
          1 -> AppTheme.ClearlyWhite
          2 -> AppTheme.KindaDark
          0 -> AppTheme.FollowSystem
          else -> AppTheme.FollowSystem
        }
        ceresPreferences.appTheme = appTheme
      },
    )
  }

  if (ceresPreferences.disableDynamicTheming) {
    advancedView(
      title = "App color theme",
      subtitle = "Try another color",
      icon = Icons.Default.ColorLens,
    ) {
      val options = listOf("Blue", "Red", "Green")
      segmentedButtons(
        options = options,
        selectedOption = 0,
        onOptionSelected = { option ->

        },
      )
    }
  }

  advancedView(
    title = "Dynamic Theming",
    subtitle = "Use Android's color style",
    icon = Icons.Default.AutoAwesome,
  ) {
    switchButton(
      switchToggled = !ceresPreferences.disableDynamicTheming,
      onSwitchToggled = { isToggled ->
        ceresPreferences.disableDynamicTheming = !isToggled
      },
    )
  }

  advancedView(
    title = "Just Black",
    subtitle = "If set on something else then `Off` it will override the App Theme value.",
    subtitleColor = ColorSchemeKeyTokens.Error,
    icon = Icons.Default.InvertColors,
    clickable = {
    },
  ) {
    val options = listOf("On", "Off", "Auto")
    segmentedButtons(
      options = options,
      selectedOption = when (ceresPreferences.justBlack) {
        JustBlackTheme.Off -> 1
        JustBlackTheme.AlwaysOn -> 0
        JustBlackTheme.FollowSystem -> 2
      },
      onOptionSelected = { option ->
        val justBlackTheme = when (option) {
          0 -> JustBlackTheme.AlwaysOn
          1 -> JustBlackTheme.Off
          2 -> JustBlackTheme.FollowSystem
          else -> JustBlackTheme.FollowSystem
        }
        ceresPreferences.justBlack = justBlackTheme
      },
    )
  }

  header {
    "Feedback"
  }

  advancedView(
    title = "Audio Feedback",
    subtitle = "Use Android's color style",
    icon = Icons.Default.Audiotrack,
    clickable = {

    },
  ) {
    switchButton(
      switchToggled = !ceresPreferences.disableAudioFeedback,
      onSwitchToggled = { isToggled ->
        ceresPreferences.disableAudioFeedback = !isToggled
      },
    )
  }

  advancedView(
    title = "Vibration Feedback",
    icon = Icons.Default.Vibration,
    clickable = {
    },
  ) {
    switchButton(
      switchToggled = !ceresPreferences.disableVibrationFeedback,
      onSwitchToggled = { isToggled ->
        ceresPreferences.disableVibrationFeedback = !isToggled
      },
    )
  }
}
