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

package dev.teogor.ceres.lib.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.screen.core.scope.ScreenListScope
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.theme.MaterialTheme
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

typealias SettingsScope = ScreenListScope

open class SettingsScreenItem

class SettingsScreenDefaultItem(
  val title: String,
  val description: String?,
  val icon: ImageVector?,
  val clickable: (() -> Unit)?,
) : SettingsScreenItem()

class SettingsScreenConfig {
  internal var items: List<SettingsScreenItem> = emptyList()
}

fun SettingsScope.settingsItems(block: MutableList<SettingsScreenItem>.() -> Unit) {
  val list = mutableListOf<SettingsScreenItem>()
  list.block()
  SettingsScreenConfig().apply {
    this.items = list
  }.let { settings ->
    settings.items.forEach { item ->
      when (item) {
        is SettingsScreenDefaultItem -> settingsItem(item)
      }
    }
  }
}

fun MutableList<SettingsScreenItem>.settingsItem(
  title: String,
  description: String? = null,
  icon: ImageVector? = null,
  clickable: (() -> Unit)? = null,
) {
  add(
    SettingsScreenDefaultItem(
      title = title,
      description = description,
      icon = icon,
      clickable = clickable,
    ),
  )
}

@OptIn(ExperimentalContracts::class)
inline fun <T> T?.perform(
  onNull: () -> Unit = {},
  block: (T) -> Unit,
) {
  contract {
    callsInPlace(block, EXACTLY_ONCE)
  }
  if (this == null) {
    onNull()
  } else {
    block(requireNotNull(this))
  }
}

fun <T : Any> T?.assertNonNull(): T {
  return requireNotNull(this) { "Value must not be null" }
}

private fun SettingsScope.settingsItem(
  item: SettingsScreenDefaultItem,
) = item {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable {
        item.clickable?.invoke()
      }
      .padding(vertical = 10.dp, horizontal = 20.dp),
    verticalAlignment = if (item.description != null) {
      Alignment.Top
    } else {
      Alignment.CenterVertically
    },
  ) {
    item.icon.perform(
      onNull = {
        Spacer(
          modifier = Modifier
            .padding(end = 12.dp)
            .size(44.dp),
        )
      },
    ) {
      Icon(
        imageVector = it,
        contentDescription = item.title,
        tint = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier
          .padding(end = 12.dp, top = 4.dp)
          .size(44.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = CircleShape,
          )
          .padding(10.dp),
      )
    }
    Column {
      Text(
        modifier = Modifier.padding(
          top = 6.dp,
          bottom = if (item.description != null) {
            2.dp
          } else {
            6.dp
          },
        ),
        text = item.title,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
      item.description?.let { description ->
        Text(
          modifier = Modifier.padding(bottom = 6.dp),
          text = description,
          fontSize = 12.sp,
          lineHeight = 12.sp,
          color = MaterialTheme.colorScheme.onSurface,
        )
      }
    }
  }
}
