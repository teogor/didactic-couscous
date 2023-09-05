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

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import dev.teogor.ceres.screen.builder.compose.SegmentedButton
import dev.teogor.ceres.screen.builder.compose.View
import dev.teogor.ceres.screen.builder.model.ConfigScreenDefaultView
import dev.teogor.ceres.screen.builder.model.CustomConfigView
import dev.teogor.ceres.ui.designsystem.Switch

internal fun BuilderListScope.aboutItem(
  item: ConfigScreenDefaultView,
) = item {
  View(item)
}

internal fun BuilderListScope.customItem(
  item: CustomConfigView,
) = item {
  item.content()
}

@Preview(showBackground = true)
@Composable
fun SegmentedButtonPreview() {
  var selectedOption by remember { mutableIntStateOf(0) }
  Column {
    SegmentedButton(
      options = listOf("Auto", "On", "Off"),
      selectedOption = selectedOption,
      onOptionSelected = { option -> selectedOption = option },
    )

    Switch(
      checked = true,
      onCheckedChange = {
      },
    )

    Switch(
      checked = false,
      onCheckedChange = {
      },
    )
  }
}
