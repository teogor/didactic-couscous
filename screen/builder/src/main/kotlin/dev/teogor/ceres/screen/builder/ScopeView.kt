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

package dev.teogor.ceres.screen.builder

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.screen.builder.compose.AdvancedView
import dev.teogor.ceres.screen.builder.compose.HeaderView
import dev.teogor.ceres.screen.builder.model.AdvancedViewBuilder
import dev.teogor.ceres.screen.builder.model.HeaderViewBuilder

internal val topPadding: Dp = 16.dp
internal val endPadding: Dp = 20.dp
internal val iconSize: Dp = 26.dp
internal val horizontalPadding: Dp = 20.dp
internal val horizontalNoIconPadding: Dp = iconSize + horizontalPadding
internal val verticalPadding: Dp = 8.dp

internal fun BuilderListScope.headerItem(
  config: HeaderViewBuilder,
) = item {
  HeaderView(config)
}

internal fun BuilderListScope.advancedView(
  config: AdvancedViewBuilder,
) = item {
  AdvancedView(config)
}
