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

package dev.teogor.ceres.lib.about

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DomainVerification
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PermDeviceInformation
import androidx.compose.material.icons.filled.Source
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.teogor.ceres.core.runtime.AppMetadataManager
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.navigateTo
import dev.teogor.ceres.framework.core.app.setScreenInfo
import dev.teogor.ceres.framework.core.screen.floatingButton
import dev.teogor.ceres.framework.core.screen.isStatusBarVisible
import dev.teogor.ceres.framework.core.screen.isVisible
import dev.teogor.ceres.framework.core.screen.showBackButton
import dev.teogor.ceres.framework.core.screen.showNavBar
import dev.teogor.ceres.framework.core.screen.showSettingsButton
import dev.teogor.ceres.framework.core.screen.toolbarTitle
import dev.teogor.ceres.framework.core.screen.toolbarTokens
import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.navigation.core.utilities.toScreenName
import dev.teogor.ceres.screen.builder.compose.HeaderView
import dev.teogor.ceres.screen.builder.compose.SimpleView
import dev.teogor.ceres.screen.core.layout.LazyColumnLayoutBase
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.theme.MaterialTheme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
internal fun AboutRoute(
  baseActions: BaseActions,
) {
  baseActions.setScreenInfo {
    showNavBar {
      false
    }
    isStatusBarVisible {
      true
    }
    toolbarTokens {
      toolbarTitle {
        "About"
      }
      showSettingsButton {
        false
      }
      showBackButton {
        true
      }
    }

    floatingButton {
      isVisible {
        false
      }
    }
  }

  AboutLayout(
    navigateTo = { destination ->
      baseActions.navigateTo(destination)
    },
  )
}

@Composable
private fun AboutLayout(
  navigateTo: (ScreenRoute) -> Unit,
) = LazyColumnLayoutBase(
  screenName = AboutScreenRoute.toScreenName(),
) {
  item {
    HeaderView(
      title = "Version Info",
    )
  }

  item {
    SimpleView(
      title = "App version",
      subtitle = AppMetadataManager.versionName,
      icon = Icons.Default.Info,
    )
  }

  item {
    SimpleView(
      title = "Ceres Framework version",
      subtitle = AppMetadataManager.ceresFrameworkVersion,
      icon = Icons.Default.PermDeviceInformation,
    )
    Text(
      text = "This app is powered by the Ceres Framework, created by developer Teodor Grigor.",
      color = MaterialTheme.colorScheme.success,
      fontSize = 10.sp,
      lineHeight = 12.sp,
    )
  }

  item {
    SimpleView(
      title = "Build date",
      subtitle = AppMetadataManager.buildDateTime.format(
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM),
      ),
      icon = Icons.Default.DateRange,
    )
  }

  item {
    HeaderView(
      title = "About Us",
    )
  }

  item {
    SimpleView(
      title = "Company",
      subtitle = "ZeoOwl",
      icon = Icons.Default.Business,
    )
  }

  item {
    SimpleView(
      title = "Made in",
      subtitle = "Brasov, Romania",
      icon = Icons.Default.LocationOn,
    )
  }

  item {
    HeaderView(
      title = "Security Patch",
    )
  }

  item {
    SimpleView(
      title = "Build hash",
      subtitle = AppMetadataManager.gitHash,
      icon = Icons.Default.DomainVerification,
    )
  }

  item {
    SimpleView(
      title = "APK hash",
      subtitle = "${AppMetadataManager.apkHash}",
      icon = Icons.Default.Verified,
    )
  }

  item {
    HeaderView(
      title = "Licenses",
    )
  }

  item {
    SimpleView(
      title = "Open-source licenses",
      subtitle = "License details for open-source software",
      icon = Icons.Default.Source,
      clickable = {
        navigateTo(AboutLibrariesScreenRoute)
      },
    )
  }

  item {
    Spacer(modifier = Modifier.height(100.dp))
  }
}
