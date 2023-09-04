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

package dev.teogor.ceres.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.teogor.ceres.feature.home.homeNavigationRoute
import dev.teogor.ceres.feature.home.homeScreen
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.CeresAppState
import dev.teogor.ceres.navigation.core.NavHost

@Composable
fun NavHost(
  modifier: Modifier = Modifier,
  startDestination: String = homeNavigationRoute,
  appState: CeresAppState,
  baseActions: BaseActions,
) {
  NavHost(
    navController = appState.navController,
    modifier = modifier,
    startDestination = startDestination,
  ) {
    homeScreen(baseActions)
  }
}
