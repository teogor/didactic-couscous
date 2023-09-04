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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun RotatableIcon(
  imageVector: ImageVector,
  rotation: Float,
  contentDescription: String,
  modifier: Modifier = Modifier,
  tint: Color = LocalContentColor.current,
) {
  val rotationState = remember { Animatable(initialValue = rotation) }

  LaunchedEffect(rotation) {
    rotationState.animateTo(rotation, animationSpec = spring())
  }

  Icon(
    imageVector = imageVector,
    contentDescription = contentDescription,
    modifier = modifier.rotate(rotationState.value),
    tint = tint,
  )
}