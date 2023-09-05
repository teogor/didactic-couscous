package dev.teogor.ceres.screen.builder.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.builder.horizontalNoIconPadding
import dev.teogor.ceres.screen.builder.horizontalPadding
import dev.teogor.ceres.screen.builder.model.HeaderViewBuilder
import dev.teogor.ceres.screen.builder.topPadding
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.theme.MaterialTheme

@Composable
fun HeaderView(
  item: HeaderViewBuilder,
) = with(item) {
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
      text = title,
      fontSize = 13.sp,
      fontWeight = FontWeight.Medium,
      textAlign = TextAlign.Start,
      color = MaterialTheme.colorScheme.secondary,
    )
  }
}
