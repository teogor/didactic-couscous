package dev.teogor.ceres.screen.builder.compose

import androidx.compose.runtime.Composable
import dev.teogor.ceres.screen.builder.model.CustomViewBuilder

@Composable
fun CustomView(
  item: CustomViewBuilder,
) = with(item) {
  content()
}
