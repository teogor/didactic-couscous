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

package dev.teogor.ceres.framework.core.deprecated.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.thumbnail.ThumbnailPlugin
import dev.teogor.ceres.core.runtime.AppMetadataManager
import dev.teogor.ceres.data.compose.rememberPreference
import dev.teogor.ceres.data.datastore.common.getPrivacyFormattedValue
import dev.teogor.ceres.data.datastore.defaults.ceresPreferences
import dev.teogor.ceres.navigation.core.LocalNavigationParameters
import dev.teogor.ceres.ui.designsystem.HorizontalDivider
import dev.teogor.ceres.ui.designsystem.Surface
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.foundation.clickable
import dev.teogor.ceres.ui.foundation.clickableSingle
import dev.teogor.ceres.ui.foundation.lib.resources.Symbols
import dev.teogor.ceres.ui.foundation.withTouchFeedback
import dev.teogor.ceres.ui.theme.MaterialTheme
import dev.teogor.ceres.ui.theme.surfaceColorAtElevation
import dev.teogor.ceres.ui.theme.tokens.ElevationTokens
import java.util.Calendar

private val cornerSize = 20.dp
private val roundedShape = RoundedCornerShape(cornerSize)
private val roundedShapeTop = RoundedCornerShape(
  topStart = cornerSize,
  topEnd = cornerSize,
)
private val roundedShapeBottom = RoundedCornerShape(
  bottomStart = cornerSize,
  bottomEnd = cornerSize,
)

typealias MenuScope = LazyListScope

open class MenuItem

class MenuItemContent(
  val content: String,
  val icon: ImageVector,
  val description: String? = null,
  val clickable: () -> Unit = {},
) : MenuItem()

class MenuDivider : MenuItem()

class CustomMenuItem(
  val content: @Composable () -> Unit,
  val clickable: () -> Unit = {},
) : MenuItem()

class MenuFooter(
  val licenseHolder: String,
  val modifier: Modifier = Modifier,
) : MenuItem()

class Menu {
  internal var topItems: List<MenuItem> = emptyList()
  internal var items: List<MenuItem> = emptyList()
}

fun MenuScope.menu(
  block: Menu.() -> Unit,
) {
  Menu().apply(block).let { menu ->
    items(
      menu.topItems.size,
    ) { index ->
      when (val item = menu.topItems[index]) {
        is MenuItemContent -> {
          MenuTopSurface(
            topRadius = index == 0,
            bottomRadius = index == menu.topItems.size - 1,
            clickable = item.clickable,
          ) {
            MenuItem(
              content = item.content,
              icon = item.icon,
            )
          }
        }

        is MenuDivider -> {
          HorizontalDivider(
            thickness = 2.dp,
            color = Color.Transparent,
          )
        }

        is CustomMenuItem -> {
          MenuTopSurface(
            topRadius = index == 0,
            bottomRadius = index == menu.topItems.size - 1,
            clickable = item.clickable,
          ) {
            item.content()
          }
        }
      }
    }
    item {
      Spacer(modifier = Modifier.height(10.dp))
    }
    items(
      menu.items.size,
    ) { index ->
      when (val item = menu.items[index]) {
        is MenuItemContent -> {
          MenuItem(
            content = item.content,
            icon = item.icon,
            clickable = item.clickable,
          )
        }

        is MenuDivider -> {
          HorizontalDivider()
        }

        is MenuFooter -> {
          MenuFooterItem(
            licenseHolder = item.licenseHolder,
            modifier = item.modifier,
          )
        }

        is CustomMenuItem -> {
          item.content()
        }
      }
    }
  }
}

fun Menu.menuTop(block: MutableList<MenuItem>.() -> Unit) {
  val list = mutableListOf<MenuItem>()
  list.block()
  this.topItems = list
}

fun Menu.menuContent(block: MutableList<MenuItem>.() -> Unit) {
  val list = mutableListOf<MenuItem>()
  list.block()
  this.items = list
}

fun MutableList<MenuItem>.menuItem(
  content: String,
  icon: ImageVector,
  description: String? = null,
  clickable: () -> Unit = {},
) {
  add(
    MenuItemContent(
      content = content,
      icon = icon,
      description = description,
      clickable = clickable,
    ),
  )
}

fun MutableList<MenuItem>.menuDivider() {
  add(
    MenuDivider(),
  )
}

fun MutableList<MenuItem>.menuFooter(
  licenseHolder: String,
  modifier: Modifier = Modifier,
) {
  add(
    MenuFooter(
      licenseHolder = licenseHolder,
      modifier = modifier,
    ),
  )
}

fun MutableList<MenuItem>.menuItem(
  clickable: () -> Unit = {},
  content: @Composable () -> Unit,
) {
  add(
    CustomMenuItem(
      content = content,
      clickable = clickable,
    ),
  )
}

fun MutableList<MenuItem>.menuUserData(
  modifier: Modifier = Modifier,
  clickable: () -> Unit = {},
  textIfEmpty: () -> String = { "User Name" },
) {
  menuItem(
    clickable = clickable,
  ) {
    Column(
      modifier = modifier
        .padding(vertical = 16.dp, horizontal = 12.dp),
    ) {
      val ceresPreferences = remember {
        ceresPreferences()
      }
      val name = rememberPreference(
        ceresPreferences.getNameFlow(),
        ceresPreferences.name,
      )
      val profileImage = rememberPreference(
        ceresPreferences.getProfileImageFlow(),
        ceresPreferences.profileImage,
      )

      Row(
        verticalAlignment = Alignment.CenterVertically,
      ) {
        if (profileImage.isNotEmpty()) {
          GlideImage(
            imageModel = { profileImage },
            imageOptions = ImageOptions(
              contentScale = ContentScale.Inside,
              alignment = Alignment.Center,
            ),
            component = rememberImageComponent {
              +ThumbnailPlugin()
            },
            modifier = Modifier
              .padding(end = 10.dp)
              .size(40.dp)
              .clip(CircleShape),
          )
        }
        Column {
          Text(
            text = "Welcome,",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Text(
            text = name.ifEmpty {
              textIfEmpty()
            },
            fontSize = 18.sp,
            modifier = Modifier
              .padding(start = 2.dp),
            color = MaterialTheme.colorScheme.onSurface,
          )
        }
      }
    }
  }
}

fun MutableList<MenuItem>.menuUserId(
  modifier: Modifier = Modifier,
) {
  menuItem {
    Column(
      modifier = modifier
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      val ceresPreferences = remember {
        ceresPreferences()
      }
      val userId = ceresPreferences.userId
      Text(
        modifier = Modifier.padding(vertical = 0.dp),
        text = "ID-${userId.getPrivacyFormattedValue(6)}",
        fontSize = 10.sp,
        lineHeight = 10.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}

@Composable
fun MenuTitle(
  title: String,
  hasCloseButton: Boolean = true,
  onClose: () -> Unit = {},
  closeIcon: ImageVector = Icons.Default.Close,
) {
  val localAppState = LocalNavigationParameters.current
  Row(
    verticalAlignment = Alignment.CenterVertically,
  ) {
    if (hasCloseButton) {
      IconButton(
        onClick = {
          localAppState.hideMenu()
          onClose()
        }.withTouchFeedback(LocalContext.current),
        modifier = Modifier
          .size(48.dp),
      ) {
        Icon(
          imageVector = closeIcon,
          contentDescription = "Close",
          tint = MaterialTheme.colorScheme.onSurface,
        )
      }
    }

    Text(
      text = title,
      fontSize = 18.sp,
      modifier = Modifier
        .weight(1f)
        .padding(end = if (hasCloseButton) 48.dp else 0.dp),
      textAlign = TextAlign.Center,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      color = MaterialTheme.colorScheme.onSurface,
    )
  }
}

@Composable
private fun MenuItem(
  content: String,
  modifier: Modifier = Modifier,
  contentDescription: String = content.lowercase(),
  icon: ImageVector? = null,
  description: String? = null,
  clickable: (() -> Unit)? = null,
) {
  val localAppState = LocalNavigationParameters.current

  Row(
    modifier = if (clickable != null) {
      modifier
        .fillMaxWidth()
        .clip(roundedShape)
        .clickableSingle {
          localAppState.hideMenu()
          clickable()
        }
        .padding(vertical = 10.dp, horizontal = 12.dp)
    } else {
      modifier
        .fillMaxWidth()
        .clip(roundedShape)
        .padding(vertical = 10.dp, horizontal = 12.dp)
    },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    icon?.let {
      Icon(
        imageVector = it,
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(end = 12.dp),
      )
    }
    Column {
      Text(
        modifier = Modifier.padding(vertical = 6.dp),
        text = content,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}

@Composable
private fun MenuFooterItem(
  licenseHolder: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    Text(
      modifier = Modifier.padding(vertical = 0.dp),
      text = "${Symbols.COPYRIGHT} $currentYear $licenseHolder. All rights reserved.",
      fontSize = 10.sp,
      color = MaterialTheme.colorScheme.onSurface,
    )
    val versionContent = if (AppMetadataManager.isDebuggable) {
      "v${AppMetadataManager.versionName} [debug]"
    } else {
      "v${AppMetadataManager.versionName}"
    }
    Text(
      modifier = Modifier.padding(vertical = 0.dp),
      text = versionContent,
      fontSize = 10.sp,
      color = MaterialTheme.colorScheme.onSurface,
    )
  }
}

@Composable
private fun MenuTopSurface(
  topRadius: Boolean,
  bottomRadius: Boolean,
  clickable: () -> Unit = {},
  content: @Composable () -> Unit,
) {
  val localAppState = LocalNavigationParameters.current
  val shape = when {
    topRadius && bottomRadius -> {
      roundedShape
    }

    topRadius -> {
      roundedShapeTop
    }

    bottomRadius -> {
      roundedShapeBottom
    }

    else -> {
      RectangleShape
    }
  }
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .clip(shape)
      .clickable {
        localAppState.hideMenu()
        clickable()
      },
    color = MaterialTheme.colorScheme.surfaceColorAtElevation(ElevationTokens.Level10),
    shape = shape,
  ) {
    content()
  }
}
