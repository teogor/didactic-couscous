package dev.teogor.ceres.screen.builder.model

import androidx.compose.ui.graphics.vector.ImageVector
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

class ItemConfigView(
  val title: String,
  val subtitle: String?,
  val subtitleColor: ColorSchemeKeyTokens?,
  val imageVector: ImageVector?,
  val clickable: (() -> Unit)? = null,
  internal var segmentedOptions: List<String>? = null,
  internal var segmentedSelectedOption: Int? = null,
  internal var segmentedOnOptionSelected: ((Int) -> Unit)? = null,
  internal var hasSwitch: Boolean = false,
) : ConfigScreenView()
