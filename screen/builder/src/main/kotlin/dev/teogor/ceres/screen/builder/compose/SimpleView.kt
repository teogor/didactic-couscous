package dev.teogor.ceres.screen.builder.compose

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.model.SimpleViewBuilder
import dev.teogor.ceres.screen.builder.utilities.perform
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme

@Composable
fun SimpleView(
  item: SimpleViewBuilder,
) {
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
