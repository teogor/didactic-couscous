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
import dev.teogor.ceres.screen.builder.compose.headerItem
import dev.teogor.ceres.screen.builder.compose.standardItem
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.tokens.ColorSchemeKeyTokens

open class ConfigScreenItem

class CustomConfigItem(
  val content: @Composable () -> Unit,
) : ConfigScreenItem()

class HeaderConfigItem(
  val title: String,
) : ConfigScreenItem()

class ItemConfigItem(
  val title: String,
  val subtitle: String?,
  val subtitleColor: ColorSchemeKeyTokens?,
  val imageVector: ImageVector?,
  val clickable: (() -> Unit)? = null,
  internal var segmentedOptions: List<String>? = null,
  internal var segmentedSelectedOption: Int? = null,
  internal var segmentedOnOptionSelected: ((Int) -> Unit)? = null,
  internal var hasSwitch: Boolean = false,
) : ConfigScreenItem()

class ConfigScreenDefaultItem(
  internal val title: String,
  internal val description: String?,
  internal val icon: ImageVector?,
  internal val clickable: (() -> Unit)?,
) : ConfigScreenItem()

class AboutScreenConfig {
  internal var items: List<ConfigScreenItem> = emptyList()
}

data class CategoryConfig(
    var modifier: Modifier = Modifier,
    var title: String,
    var titleModifier: Modifier = Modifier,
    var elements: MutableList<ConfigScreenItem>,
) : ConfigScreenItem()

data class SubcategoryConfig(
  var title: String?,
  var content: String,
  var singleLine: Boolean = false,
  var modifier: Modifier = Modifier,
) : ConfigScreenItem()

// endregion

fun BuilderListScope.screenItems(block: MutableList<ConfigScreenItem>.() -> Unit) {
  val list = mutableListOf<ConfigScreenItem>()
  list.block()
  AboutScreenConfig().apply {
    this.items = list
  }.let { about ->
    about.items.forEach { item ->
      when (item) {
        is ConfigScreenDefaultItem -> aboutItem(item)
        is CustomConfigItem -> customItem(item)
        is HeaderConfigItem -> headerItem(item)
        is ItemConfigItem -> standardItem(item)
      }
    }
  }
}

fun MutableList<ConfigScreenItem>.customItem(
  content: @Composable () -> Unit,
) {
  add(
    CustomConfigItem(content = content),
  )
}

inline fun MutableList<ConfigScreenItem>.header(
  crossinline title: () -> String,
) {
  add(
    HeaderConfigItem(title = title()),
  )
}

inline fun MutableList<ConfigScreenItem>.item(
    title: String,
    subtitle: String? = null,
    subtitleColor: ColorSchemeKeyTokens? = null,
    imageVector: ImageVector? = null,
    noinline clickable: (() -> Unit)? = null,
    crossinline block: ItemConfigItem.() -> Unit = {},
) {
  add(
    ItemConfigItem(
      title = title,
      subtitle = subtitle,
      subtitleColor = subtitleColor,
      imageVector = imageVector,
      clickable = clickable,
    ).apply(block),
  )
}

fun ItemConfigItem.segmentedButtons(
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
    CustomConfigItem(
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

fun MutableList<ConfigScreenItem>.aboutItem(
  title: String,
  description: String? = null,
  icon: ImageVector? = null,
  clickable: (() -> Unit)? = null,
) {
  add(
    ConfigScreenDefaultItem(
      title = title,
      description = description,
      icon = icon,
      clickable = clickable,
    ),
  )
}
