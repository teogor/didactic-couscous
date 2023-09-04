package dev.teogor.ceres.theme

import androidx.compose.ui.graphics.Color
import dev.teogor.ceres.framework.core.model.ThemeBuilder
import dev.teogor.ceres.ui.spectrum.utilities.asHexColor

private val color = Color(39, 158, 31, 255)

fun configureTheme() = ThemeBuilder(
  themeSeed = color.asHexColor(),
)
