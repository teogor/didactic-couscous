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

package dev.teogor.ceres.monetisation.admob.nativead

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import dev.teogor.ceres.ui.designsystem.buttonColorsDefaults

@Composable
fun AdActionButton(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
  colors: ButtonColors = buttonColorsDefaults(),
  shape: Shape = ButtonDefaults.shape,
  content: @Composable (RowScope.() -> Unit),
) {
  val containerColor by rememberUpdatedState(
    if (enabled) colors.containerColor else colors.disabledContainerColor,
  )
  val contentColor by rememberUpdatedState(
    if (enabled) colors.contentColor else colors.disabledContentColor,
  )

  Surface(
    modifier = modifier
      .semantics { role = Role.Button },
    color = containerColor,
    shape = shape,
    contentColor = contentColor,
  ) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
      ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
        Row(
          Modifier
            .defaultMinSize(
              minWidth = ButtonDefaults.MinWidth,
              minHeight = ButtonDefaults.MinHeight,
            )
            .padding(contentPadding),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
          content = content,
        )
      }
    }
  }
}
