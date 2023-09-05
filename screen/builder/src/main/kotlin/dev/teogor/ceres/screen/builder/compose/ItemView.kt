package dev.teogor.ceres.screen.builder.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.BuilderListScope
import dev.teogor.ceres.screen.builder.model.HeaderConfigView
import dev.teogor.ceres.screen.builder.model.ItemConfigView
import dev.teogor.ceres.ui.designsystem.Switch
import dev.teogor.ceres.ui.designsystem.Text
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
  config: HeaderConfigView,
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
  config: ItemConfigView,
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
