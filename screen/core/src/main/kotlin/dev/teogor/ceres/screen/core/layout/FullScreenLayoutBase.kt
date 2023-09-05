package dev.teogor.ceres.screen.core.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.teogor.ceres.navigation.events.TrackScreenViewEvent
import dev.teogor.ceres.ui.theme.MaterialTheme

@Composable
fun FullScreenLayoutBase(
  screenName: String? = null,
  backgroundColor: Color = MaterialTheme.colorScheme.background,
  content: @Composable BoxScope.() -> Unit,
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color = backgroundColor)
      .statusBarsPadding(),
    content = content,
  )

  screenName?.let {
    TrackScreenViewEvent(screenName = it)
  }
}
