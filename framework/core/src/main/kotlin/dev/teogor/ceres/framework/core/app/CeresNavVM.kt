package dev.teogor.ceres.framework.core.app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class CeresNavVM : ViewModel() {
  val toolbarAlpha = StateFlowEvent(defaultValue = 0f)
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
    toolbarAlpha.setValue(value)
  }
}

val LocalCeresNavVM = staticCompositionLocalOf<CeresNavVM> {
  error("No CeresNavVM provided")
}
