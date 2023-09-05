package dev.teogor.ceres.screen.builder.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.endPadding
import dev.teogor.ceres.screen.builder.horizontalNoIconPadding
import dev.teogor.ceres.screen.builder.horizontalPadding
import dev.teogor.ceres.screen.builder.iconSize
import dev.teogor.ceres.screen.builder.model.AdvancedViewBuilder
import dev.teogor.ceres.screen.builder.verticalPadding
import dev.teogor.ceres.ui.designsystem.Switch
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme
import dev.teogor.ceres.ui.theme.toColor

@Composable
fun AdvancedView(
  config: AdvancedViewBuilder,
) {
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
