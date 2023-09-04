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

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.ui.designsystem.color.ColorBar
import dev.teogor.ceres.ui.designsystem.color.ColorBarStyle
import dev.teogor.ceres.core.designsystem.foundation.clickable
import dev.teogor.ceres.ui.theme.core.SurfaceOverlay
import dev.teogor.ceres.core.designsystem.ui.addSound
import dev.teogor.ceres.ui.theme.MaterialTheme

@Composable
fun ElementView(
  title: String = "",
  subtitle: String = "",
  onClick: () -> Unit = {},
  showSwitch: Boolean = false,
  isChecked: Boolean = false,
  enabled: Boolean = true,
  onCheckedChange: (Boolean) -> Unit = {},
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit = {},
) {
  val colorScheme = MaterialTheme.colorScheme
  val surfaceOverlay = SurfaceOverlay(
    themeOverlayColor = colorScheme.surface.toArgb(),
    themeSurfaceColor = colorScheme.primary.toArgb(),
  )
  val background = surfaceOverlay.forAlpha(overlayAlpha = 0.1f)

  Surface(
    shape = RoundedCornerShape(16.dp),
    color = background,
    modifier = modifier
      .fillMaxWidth()
      .clip(shape = RoundedCornerShape(16.dp))
      .clickable(
        enabled = enabled,
        onClick = {
          addSound()
          if (showSwitch) {
            onCheckedChange(!isChecked)
          } else {
            onClick()
          }
        },
        indication = rememberRipple(
          color = colorScheme.primary,
        ),
        interactionSource = remember { MutableInteractionSource() },
      )
      .alpha(if (enabled) 1f else .6f)
      .focusable(),
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(
        modifier = Modifier.weight(1f),
      ) {
        if (title.isNotEmpty()) {
          Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = if (subtitle.isNotEmpty()) 8.dp else 0.dp),
          )
        }
        if (subtitle.isNotEmpty()) {
          Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
          )
        }
        content()
      }
      if (showSwitch) {
        Switch(
          checked = isChecked,
          onCheckedChange = {
            addSound()
            onCheckedChange(it)
          },
        )
      }
    }
  }
}

@Composable
fun ElementViewWithColorBar(
  title: String = "",
  subtitle: String = "",
  onClick: () -> Unit = {},
  colorBarEnabled: Boolean = true,
  enabled: Boolean = true,
  colorBarStyle: ColorBarStyle = ColorBarStyle(),
  colors: List<Color> = listOf(Color.Blue, Color.Yellow, Color.Red),
  onHsvColorChanged: (Float) -> Unit = {},
  onColorChanged: (String) -> Unit = {},
  initialColor: Color = colors.first(),
  modifier: Modifier = Modifier,
) {
  ElementView(
    title = title,
    subtitle = subtitle,
    onClick = onClick,
    showSwitch = false,
    modifier = modifier,
    enabled = enabled,
  ) {
    if (colorBarEnabled) {
      ColorBar(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 14.dp)
          .padding(horizontal = 8.dp)
          .height(10.dp),
        style = colorBarStyle,
        colors = colors,
        onHsvColorChanged = onHsvColorChanged,
        onColorChanged = onColorChanged,
        initialColor = initialColor,
        enabled = enabled,
      )
    }
  }
}
