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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.screen.builder.BuilderListScope
import dev.teogor.ceres.screen.builder.model.HeaderConfigView
import dev.teogor.ceres.screen.builder.model.ItemConfigView

internal val topPadding: Dp = 16.dp
internal val endPadding: Dp = 20.dp
internal val iconSize: Dp = 26.dp
internal val horizontalPadding: Dp = 20.dp
internal val horizontalNoIconPadding: Dp = iconSize + horizontalPadding
internal val verticalPadding: Dp = 8.dp

internal fun BuilderListScope.headerItem(
  config: HeaderConfigView,
) = item {
  HeaderView(config)
}

// todo rename
internal fun BuilderListScope.standardItem(
  config: ItemConfigView,
) = item {
  StandardView(config)
}
