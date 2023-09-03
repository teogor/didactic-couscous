package dev.teogor.ceres.ui.colourgenerator.utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.asIntColor(): Int {
    return this.toArgb()
}