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
import dev.teogor.ceres.screen.core.ColumnScreen
import dev.teogor.ceres.ui.designsystem.Text

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

  HomeScreen()
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
