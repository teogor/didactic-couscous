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

import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import dev.teogor.ceres.framework.core.Activity
import dev.teogor.ceres.framework.core.beta.MenuConfig
import dev.teogor.ceres.framework.core.beta.NavGraphOptions
import dev.teogor.ceres.menu.applyMenuConfig
import dev.teogor.ceres.navigation.ApplyNavHost
import dev.teogor.ceres.navigation.core.menu.TopLevelDestination

@AndroidEntryPoint
class MainActivity : Activity() {

  // todo based on the screen we may have different top level destinations
  override val topLevelDestinations: List<TopLevelDestination>
    get() = super.topLevelDestinations

  @Composable
  override fun NavGraphOptions.BuildNavGraph() = ApplyNavHost()

  @Composable
  override fun MenuConfig.buildMenu() = applyMenuConfig()

}
