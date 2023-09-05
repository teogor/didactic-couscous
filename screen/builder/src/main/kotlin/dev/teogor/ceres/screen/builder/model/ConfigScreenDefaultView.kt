package dev.teogor.ceres.screen.builder.model

import androidx.compose.ui.graphics.vector.ImageVector

class ConfigScreenDefaultView(
  internal val title: String,
  internal val description: String?,
  internal val icon: ImageVector?,
  internal val clickable: (() -> Unit)?,
) : ConfigScreenView()
