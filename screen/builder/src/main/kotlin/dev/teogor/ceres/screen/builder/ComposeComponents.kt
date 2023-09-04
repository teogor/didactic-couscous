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
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.builder.utilities.perform
import dev.teogor.ceres.screen.core.LazyColumnScreen
import dev.teogor.ceres.ui.designsystem.Switch
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.designsystem.VerticalDivider
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme
import dev.teogor.ceres.ui.theme.toColor

@Composable
inline fun Layout(
  screenName: ScreenRoute,
  hasScrollbar: Boolean = true,
  noinline bottomContent: (@Composable () -> Unit)? = null,
  crossinline block: MutableList<ConfigScreenItem>.() -> Unit,
) = LazyColumnScreen(
  screenName = screenName.toScreenName(),
  hasScrollbarBackground = false,
  hasScrollbar = hasScrollbar,
  bottomContent = bottomContent,
) {
  screenItems {
    block()
  }
}

internal fun BuilderListScope.aboutItem(
  item: ConfigScreenDefaultItem,
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
  item: CustomConfigItem,
) = item {
  item.content()
}

internal fun BuilderListScope.categoryItem(
  config: CategoryConfig,
) = item {
  Column(
    modifier = config.modifier
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      modifier = config.titleModifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
      text = config.title,
      fontSize = 16.sp,
      textAlign = TextAlign.Start,
      color = MaterialTheme.colorScheme.onSurface,
    )
    config.elements.forEach { element ->
      when (element) {
        is SubcategoryConfig -> {
          AboutItem(element)
        }

        is CustomConfigItem -> {
          element.content()
        }
      }
    }
  }
}

private val topPadding: Dp = 16.dp
private val startPadding: Dp = 60.dp
private val endPadding: Dp = 20.dp

internal fun BuilderListScope.headerItem(
  config: HeaderConfigItem,
) = item {
  Column(
    modifier = Modifier
      .padding(top = topPadding)
      .padding(start = startPadding),
  ) {
    Text(
      text = config.title,
      fontSize = 13.sp,
      fontWeight = FontWeight.Medium,
      textAlign = TextAlign.Start,
      color = MaterialTheme.colorScheme.secondary,
    )
  }
}

internal fun BuilderListScope.standardItem(
  config: ItemConfigItem,
) = item {
  Column(
    modifier = Modifier
      .padding(top = topPadding)
      .padding(start = startPadding),
  ) {
    Text(
      text = config.title,
      fontSize = 15.sp,
      textAlign = TextAlign.Start,
      color = MaterialTheme.colorScheme.onSurface,
    )
    config.subtitle?.let { subtitle ->
      val subtitleTextColor = if (config.subtitleColor != null) {
        config.subtitleColor.toColor()
      } else {
        MaterialTheme.colorScheme.onSurface
      }
      Text(
        modifier = Modifier.padding(
          top = 1.dp,
          end = endPadding,
        ),
        text = subtitle,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Start,
        color = subtitleTextColor,
      )
    }
    config.segmentedOptions?.let { segmentedOptions ->
      var selectedOption by remember { mutableIntStateOf(config.segmentedSelectedOption ?: -1) }

      SegmentedButton(
        modifier = Modifier.padding(top = 10.dp),
        options = segmentedOptions,
        selectedOption = selectedOption,
        onOptionSelected = { option ->
          selectedOption = option
          config.segmentedOnOptionSelected?.invoke(option)
        },
      )
    }

    if (config.hasSwitch) {
      Switch(
        checked = true,
        onCheckedChange = {

        },
      )
    }
  }
}

@Composable
fun AboutItem(
  element: SubcategoryConfig,
) {
  with(element) {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 20.dp),
      verticalAlignment = Alignment.Top,
    ) {
      Column {
        title?.let { title ->
          Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = title,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            color = MaterialTheme.colorScheme.onSurface,
          )
        }
        Text(
          text = content,
          fontSize = 12.sp,
          lineHeight = 12.sp,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          maxLines = if (singleLine) 1 else Int.MAX_VALUE,
          overflow = if (singleLine) TextOverflow.Ellipsis else TextOverflow.Clip,
        )
      }
    }
  }
}

@Composable
fun SegmentedButton(
  options: List<String>,
  selectedOption: Int,
  onOptionSelected: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  val backgroundOutline = MaterialTheme.colorScheme.outline
  var selectedOptionRem by remember { mutableIntStateOf(selectedOption) }

  Row(
    modifier = modifier
      .horizontalScroll(rememberScrollState())
      .padding(end = 20.dp)
      .border(
        width = 1.dp,
        color = backgroundOutline,
        shape = MaterialTheme.shapes.extraLarge,
      ),
  ) {
    options.forEachIndexed { index, option ->
      val cornerShape = when (index) {
        0 -> MaterialTheme.shapes.extraLarge.copy(
          topEnd = ZeroCornerSize,
          bottomEnd = ZeroCornerSize,
        )

        options.size - 1 -> MaterialTheme.shapes.extraLarge.copy(
          topStart = ZeroCornerSize,
          bottomStart = ZeroCornerSize,
        )

        else -> RectangleShape
      }

      Box(
        modifier = if (index == selectedOption) {
          Modifier
            .height(40.dp)
            .widthIn(min = 80.dp, max = Dp.Unspecified)
            .clip(cornerShape)
            .background(
              color = MaterialTheme.colorScheme.secondaryContainer,
              shape = cornerShape,
            )
            .clickable {
              selectedOptionRem = index
              onOptionSelected(index)
            }
            .padding(horizontal = 20.dp)
        } else {
          Modifier
            .height(40.dp)
            .widthIn(min = 80.dp, max = Dp.Unspecified)
            .clip(cornerShape)
            .clickable {
              selectedOptionRem = index
              onOptionSelected(index)
            }
            .padding(horizontal = 20.dp)
        },
      ) {
        Text(
          text = option,
          color = MaterialTheme.colorScheme.onSurface,
          style = MaterialTheme.typography.labelMedium,
          modifier = Modifier.align(Alignment.Center),
        )
      }

      if (index < options.size - 1) {
        VerticalDivider(
          color = backgroundOutline,
          thickness = 1.dp,
          modifier = Modifier.height(40.dp),
        )
      }
    }
  }
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
