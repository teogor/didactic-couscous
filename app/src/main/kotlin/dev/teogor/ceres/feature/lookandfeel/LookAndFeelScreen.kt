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

package dev.teogor.ceres.feature.lookandfeel

import androidx.compose.runtime.Composable
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.setScreenInfo
import dev.teogor.ceres.framework.core.screen.floatingButton
import dev.teogor.ceres.framework.core.screen.isStatusBarVisible
import dev.teogor.ceres.framework.core.screen.isVisible
import dev.teogor.ceres.framework.core.screen.showBackButton
import dev.teogor.ceres.framework.core.screen.showNavBar
import dev.teogor.ceres.framework.core.screen.showSettingsButton
import dev.teogor.ceres.framework.core.screen.toolbarTitle
import dev.teogor.ceres.framework.core.screen.toolbarTokens
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.core.layout.LazyColumnLayoutBase
import dev.teogor.ceres.screen.ui.lookandfeel.LookAndFeelScreenRoute
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelAppTheme
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelColorTheme
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelDynamicTheming
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelHeaderAppearance
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelHeaderFeedback
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelJustBlack
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelSoundFeedback
import dev.teogor.ceres.screen.ui.lookandfeel.lookAndFeelVibrationFeedback

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

  LookAndFeelLayout()
}

@Composable
private fun LookAndFeelLayout() = LazyColumnLayoutBase(
  screenName = LookAndFeelScreenRoute.toScreenName(),
) {
  lookAndFeelHeaderAppearance()

  lookAndFeelAppTheme()

  lookAndFeelDynamicTheming()

  lookAndFeelColorTheme()

  lookAndFeelJustBlack()

  lookAndFeelHeaderFeedback()

  lookAndFeelSoundFeedback()

  lookAndFeelVibrationFeedback()
}
