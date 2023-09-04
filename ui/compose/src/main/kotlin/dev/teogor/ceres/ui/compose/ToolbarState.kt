package dev.teogor.ceres.ui.compose

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ToolbarState {
  private val _toolbarAlpha = mutableFloatStateOf(0f)
  val toolbarAlpha: State<Float> = _toolbarAlpha
  private val _elevation = mutableStateOf(0.dp)
  val elevation: State<Dp> = _elevation
  private val _toolbarColor = mutableStateOf(Color.Unspecified)
  val toolbarColor: State<Color> = _toolbarColor

  fun updateToolbarColor(value: Color) {
    _toolbarColor.value = value
  }

  fun updateElevation(value: Dp) {
    _elevation.value = value
  }

  fun updateToolbarAlpha(value: Float) {
    _toolbarAlpha.floatValue = value
  }
}

val LocalToolbarState = staticCompositionLocalOf<ToolbarState> {
  error("No ToolbarState provided")
}
