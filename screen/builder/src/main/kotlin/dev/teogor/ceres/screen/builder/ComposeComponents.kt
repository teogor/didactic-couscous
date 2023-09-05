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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.compose.SegmentedButton
import dev.teogor.ceres.screen.builder.model.ConfigScreenDefaultView
import dev.teogor.ceres.screen.builder.model.CustomConfigView
import dev.teogor.ceres.screen.builder.utilities.perform
import dev.teogor.ceres.ui.designsystem.Switch
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme

internal fun BuilderListScope.aboutItem(
  item: ConfigScreenDefaultView,
) = item {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable {
        item.clickable?.invoke()
      }
      .padding(vertical = 12.dp, horizontal = 20.dp),
    verticalAlignment = if (item.description != null) {
      Alignment.Top
    } else {
      Alignment.CenterVertically
    },
  ) {
    item.icon.perform(
      onNull = {
        Spacer(
          modifier = Modifier
            .padding(end = 12.dp)
            .size(44.dp),
        )
      },
    ) {
      Icon(
        imageVector = it,
        contentDescription = item.title,
        tint = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier
          .padding(end = 12.dp, top = 4.dp)
          .size(44.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = CircleShape,
          )
          .padding(10.dp),
      )
    }
    Column {
      Text(
        modifier = Modifier.padding(
          top = 6.dp,
          bottom = if (item.description != null) {
            2.dp
          } else {
            6.dp
          },
        ),
        text = item.title,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
      item.description?.let { description ->
        Text(
          modifier = Modifier.padding(bottom = 6.dp),
          text = description,
          fontSize = 12.sp,
          lineHeight = 12.sp,
          color = MaterialTheme.colorScheme.onSurface,
        )
      }
    }
  }
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
