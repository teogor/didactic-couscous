/*
 * Copyright 2023 teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.ceres.screen.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import dev.teogor.ceres.screen.builder.compose.HeaderView
import dev.teogor.ceres.screen.builder.compose.StandardView
import dev.teogor.ceres.screen.builder.compose.View
import dev.teogor.ceres.screen.builder.compose.headerItem
import dev.teogor.ceres.screen.builder.compose.standardItem
import dev.teogor.ceres.screen.builder.model.ConfigScreenDefaultView
import dev.teogor.ceres.screen.builder.model.ConfigScreenView
import dev.teogor.ceres.screen.builder.model.CustomConfigView
import dev.teogor.ceres.screen.builder.model.HeaderConfigView
import dev.teogor.ceres.screen.builder.model.ItemConfigView
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

class AboutScreenConfig {
  internal var items: List<ConfigScreenView> = emptyList()
}

data class CategoryConfig(
  var modifier: Modifier = Modifier,
  var title: String,
  var titleModifier: Modifier = Modifier,
  var elements: MutableList<ConfigScreenView>,
) : ConfigScreenView()

data class SubcategoryConfig(
  var title: String?,
  var content: String,
  var singleLine: Boolean = false,
  var modifier: Modifier = Modifier,
) : ConfigScreenView()

// endregion

fun BuilderListScope.screenItems(block: MutableList<ConfigScreenView>.() -> Unit) {
  val list = mutableListOf<ConfigScreenView>()
  list.block()
  AboutScreenConfig().apply {
    this.items = list
  }.let { about ->
    about.items.forEach { item ->
      when (item) {
        is ConfigScreenDefaultView -> aboutItem(item)
        is CustomConfigView -> customItem(item)
        is HeaderConfigView -> headerItem(item)
        is ItemConfigView -> standardItem(item)
      }
    }
  }
}

@Composable
fun BuilderColumnScope.ScreenItems(block: MutableList<ConfigScreenView>.() -> Unit) {
  val list = mutableListOf<ConfigScreenView>()
  list.block()
  AboutScreenConfig().apply {
    this.items = list
  }.let { about ->
    about.items.forEach { item ->
      when (item) {
        is ConfigScreenDefaultView -> View(item)
        is CustomConfigView -> item.content()
        is HeaderConfigView -> HeaderView(item)
        is ItemConfigView -> StandardView(item)
      }
    }
  }
}

fun MutableList<ConfigScreenView>.customItem(
  content: @Composable () -> Unit,
) {
  add(
    CustomConfigView(content = content),
  )
}

inline fun MutableList<ConfigScreenView>.header(
  crossinline title: () -> String,
) {
  add(
    HeaderConfigView(title = title()),
  )
}

inline fun MutableList<ConfigScreenView>.item(
  title: String,
  subtitle: String? = null,
  subtitleColor: ColorSchemeKeyTokens? = null,
  imageVector: ImageVector? = null,
  noinline clickable: (() -> Unit)? = null,
  crossinline block: ItemConfigView.() -> Unit = {},
) {
  add(
    ItemConfigView(
      title = title,
      subtitle = subtitle,
      subtitleColor = subtitleColor,
      imageVector = imageVector,
      clickable = clickable,
    ).apply(block),
  )
}

fun ItemConfigView.segmentedButtons(
  options: List<String>,
  selectedOption: Int? = null,
  onOptionSelected: ((Int) -> Unit)? = null,
) {
  this.segmentedOptions = options
  this.segmentedSelectedOption = selectedOption
  this.segmentedOnOptionSelected = onOptionSelected
}

inline fun CategoryConfig.title(
  modifier: Modifier = Modifier,
  crossinline content: () -> String,
) {
  title = content()
  titleModifier = modifier
}

fun CategoryConfig.customItem(
  content: @Composable () -> Unit,
) {
  elements.add(
    CustomConfigView(
      content = content,
    ),
  )
}

inline fun CategoryConfig.item(
  noinline predicate: (() -> Boolean)? = null,
  modifier: Modifier = Modifier.clickable {},
  crossinline block: SubcategoryConfig.() -> Unit,
) {
  val isVisible = if (predicate != null) {
    predicate()
  } else {
    true
  }
  if (isVisible) {
    val subcategoryConfig = SubcategoryConfig(
      title = "",
      content = "",
      modifier = modifier,
    )
    subcategoryConfig.block()
    elements.add(subcategoryConfig)
  }
}

inline fun SubcategoryConfig.title(crossinline block: () -> String) {
  title = block()
}

inline fun SubcategoryConfig.content(crossinline block: () -> String) {
  content = block()
}

inline fun SubcategoryConfig.singleLine(crossinline block: () -> Boolean) {
  singleLine = block()
}

fun MutableList<ConfigScreenView>.aboutItem(
  title: String,
  description: String? = null,
  icon: ImageVector? = null,
  clickable: (() -> Unit)? = null,
) {
  add(
    ConfigScreenDefaultView(
      title = title,
      description = description,
      icon = icon,
      clickable = clickable,
    ),
  )
}
