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

package dev.teogor.ceres.ui.designsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.ui.theme.MaterialTheme

@Composable
fun ComponentSurface(
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colorScheme.secondaryContainer,
  title: String? = null,
  titleAlign: TextAlign = TextAlign.Center,
  titleStyle: TextStyle = MaterialTheme.typography.bodyLarge,
  titleModifier: Modifier = Modifier,
  holderModifier: Modifier = Modifier,
  cornerRadius: Dp = 16.dp,
  shape: Shape = RoundedCornerShape(cornerRadius),
  content: @Composable (ColumnScope) -> Unit = {},
) {
  Surface(
    shape = shape,
    tonalElevation = 12.dp,
    color = color,
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clip(shape),
  ) {
    Column(
      modifier = holderModifier
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      if (!title.isNullOrEmpty()) {
        Text(
          text = title,
          textAlign = titleAlign,
          style = titleStyle,
          modifier = titleModifier
            .fillMaxWidth(),
        )
      }
      if (content != {}) {
        content(this)
      }
    }
  }
}
