package dev.teogor.ceres.screen.builder.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.BuilderListScope
import dev.teogor.ceres.screen.builder.HeaderConfigItem
import dev.teogor.ceres.screen.builder.ItemConfigItem
import dev.teogor.ceres.ui.designsystem.Switch
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.designsystem.VerticalDivider
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme
import dev.teogor.ceres.ui.theme.toColor


private val topPadding: Dp = 16.dp
private val endPadding: Dp = 20.dp
private val iconSize: Dp = 26.dp
private val horizontalPadding: Dp = 20.dp
private val horizontalNoIconPadding: Dp = iconSize + horizontalPadding
private val verticalPadding: Dp = 8.dp

internal fun BuilderListScope.headerItem(
  config: HeaderConfigItem,
) = item {
  Column(
    modifier = Modifier
      .padding(
        top = topPadding,
      )
      .padding(
        horizontal = horizontalPadding,
      )
      .padding(
        start = horizontalNoIconPadding,
      ),
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

// todo rename
internal fun BuilderListScope.standardItem(
  config: ItemConfigItem,
) = item {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(
        enabled = config.clickable != null,
      ) {
        config.clickable?.invoke()
      }
      .padding(
        top = verticalPadding,
        bottom = verticalPadding,
        start = horizontalPadding,
        end = horizontalPadding,
      ),
  ) {
    config.imageVector?.let {
      Icon(
        imageVector = it,
        contentDescription = config.title,
        modifier = Modifier
          .padding(top = 10.dp)
          .size(iconSize),
        tint = MaterialTheme.colorScheme.secondary,
      )
    }
    Column(
      modifier = Modifier.padding(
        start = if (config.imageVector != null) {
          horizontalPadding
        } else {
          horizontalNoIconPadding
        },
      ),
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
