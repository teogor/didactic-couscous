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

package dev.teogor.ceres

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.teogor.ceres.framework.core.Activity
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.CeresAppState
import dev.teogor.ceres.navigation.StatsNavHost
import dev.teogor.ceres.navigation.core.menu.TopLevelDestination

@AndroidEntryPoint
class MainActivity : Activity() {

  override val topLevelDestinations: List<TopLevelDestination>
    get() = super.topLevelDestinations

  @Composable
  override fun Content(
    windowSizeClass: WindowSizeClass,
    ceresAppState: CeresAppState,
    baseActions: BaseActions,
    padding: PaddingValues
  ) {
    super.Content(windowSizeClass, ceresAppState, baseActions, padding)

    StatsNavHost(
      modifier = Modifier.padding(
        bottom = padding.calculateBottomPadding(),
        top = padding.calculateTopPadding(),
      ),
      appState = ceresAppState,
      baseActions = baseActions,
    )
  }

}
