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

package dev.teogor.ceres.screen.builder.model

import androidx.compose.ui.graphics.vector.ImageVector
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

class ItemConfigView(
  val title: String,
  val subtitle: String?,
  val subtitleColor: ColorSchemeKeyTokens?,
  val imageVector: ImageVector?,
  val clickable: (() -> Unit)? = null,
  internal var segmentedOptions: List<String>? = null,
  internal var segmentedSelectedOption: Int? = null,
  internal var segmentedOnOptionSelected: ((Int) -> Unit)? = null,
  internal var hasSwitch: Boolean = false,
) : ConfigScreenView()
