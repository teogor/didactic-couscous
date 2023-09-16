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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.setScreenInfo
import dev.teogor.ceres.framework.core.screen.floatingButton
import dev.teogor.ceres.framework.core.screen.isStatusBarVisible
import dev.teogor.ceres.framework.core.screen.isVisible
import dev.teogor.ceres.framework.core.screen.showNavBar
import dev.teogor.ceres.framework.core.screen.showSettingsButton
import dev.teogor.ceres.framework.core.screen.toolbarTitle
import dev.teogor.ceres.framework.core.screen.toolbarTokens
import dev.teogor.ceres.monetisation.messaging.ConsentManager
import dev.teogor.ceres.monetisation.messaging.ConsentResult
import dev.teogor.ceres.screen.builder.advancedView
import dev.teogor.ceres.screen.builder.compose.ColumnLayout
import dev.teogor.ceres.screen.builder.header
import dev.teogor.ceres.screen.builder.segmentedButtons
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

// todo better way to configure this. perhaps use kotlin builder syntax
@Composable
internal fun HomeRoute(
  baseActions: BaseActions,
) {
  val state by remember { ConsentManager.state }
  val canRequestAds: Boolean = remember(state) {
    when (val consentResult = state) {
      is ConsentResult.ConsentFormAcquired -> consentResult.canRequestAds
      is ConsentResult.ConsentFormDismissed -> consentResult.canRequestAds
      else -> false
    }
  }

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

  if (canRequestAds) {
    HomeScreen()
  } else {
    ConsentManager.loadAndShowConsentFormIfRequired()
  }
}

@Composable
private fun HomeScreen() = ColumnLayout(
  hasScrollbarBackground = false,
  screenName = HomeScreenConfig,
) {
  header {
    "Time Interval Options"
  }

  repeat(20) {
    advancedView(
      title = "Timer Duration",
      subtitle = "Adjust the duration of the timer",
      subtitleColor = ColorSchemeKeyTokens.Error,
      clickable = {
        if (it == 0) {
          ConsentManager.loadAndShowConsentFormIfRequired()
        } else if (it == 1) {
          ConsentManager.resetConsent()
        }
      },
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
}
