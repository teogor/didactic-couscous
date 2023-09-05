package dev.teogor.ceres.screen.builder.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.designsystem.VerticalDivider
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme

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
