package dev.teogor.ceres.screen.builder.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.endPadding
import dev.teogor.ceres.screen.builder.horizontalNoIconPadding
import dev.teogor.ceres.screen.builder.horizontalPadding
import dev.teogor.ceres.screen.builder.iconSize
import dev.teogor.ceres.screen.builder.model.SimpleViewBuilder
import dev.teogor.ceres.screen.builder.utilities.perform
import dev.teogor.ceres.screen.builder.verticalPadding
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme
import dev.teogor.ceres.ui.theme.toColor

fun addExtraPadding(enabled: Boolean) = if(enabled) 0.dp else 0.dp//9.dp

@Composable
fun SimpleView(
  item: SimpleViewBuilder,
) = with(item) {
  val hasFilledIconUI = false//Random.nextBoolean()
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable {
        clickable?.invoke()
      }
      .padding(
        top = verticalPadding,
        bottom = verticalPadding,
        start = horizontalPadding + addExtraPadding(hasFilledIconUI && icon != null),
        end = horizontalPadding,
      ),
    verticalAlignment = if (subtitle != null) {
      Alignment.Top
    } else {
      Alignment.CenterVertically
    },
  ) {
    icon.perform {
      if(hasFilledIconUI) {
        Icon(
          imageVector = it,
          contentDescription = title,
          tint = MaterialTheme.colorScheme.onPrimaryContainer,
          modifier = Modifier
            .size(44.dp)
            .background(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = CircleShape,
            )
            .padding(10.dp)
            .align(Alignment.CenterVertically),
        )
      } else {
        Icon(
          imageVector = it,
          contentDescription = title,
          modifier = Modifier
            .size(iconSize)
            .align(Alignment.CenterVertically),
          tint = MaterialTheme.colorScheme.secondary,
        )
      }
    }
    Column(
      modifier = Modifier
        .padding(
          start = if (icon != null) {
            horizontalPadding + addExtraPadding(hasFilledIconUI)
          } else {
            horizontalNoIconPadding + addExtraPadding(false)
          },
        )
        .defaultMinSize(minHeight = 50.dp),
      verticalArrangement = Arrangement.Center,
    ) {
      Text(
        text = title,
        fontSize = 15.sp,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onSurface,
      )
      subtitle?.let { subtitle ->
        val subtitleTextColor = subtitleColor?.toColor() ?: MaterialTheme.colorScheme.onSurface
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
    }
  }
}
