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

package dev.teogor.ceres.screen.builder.compose

import androidx.compose.runtime.Composable
import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.builder.ScreenItems
import dev.teogor.ceres.screen.builder.model.ConfigScreenView
import dev.teogor.ceres.screen.core.ColumnScreen

@Composable
inline fun ColumnLayout(
  screenName: ScreenRoute,
  hasScrollbar: Boolean = true,
  hasScrollbarBackground: Boolean = true,
  noinline bottomContent: (@Composable () -> Unit)? = null,
  crossinline block: MutableList<ConfigScreenView>.() -> Unit,
) = ColumnScreen(
  screenName = screenName.toScreenName(),
  hasScrollbarBackground = hasScrollbarBackground,
  hasScrollbar = hasScrollbar,
  bottomContent = bottomContent,
) {
  ScreenItems {
    block()
  }
}
