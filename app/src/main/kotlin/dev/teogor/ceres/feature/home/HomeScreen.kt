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

package dev.teogor.ceres.feature.home

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.ads.nativead.NativeAd
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.setScreenInfo
import dev.teogor.ceres.framework.core.screen.floatingButton
import dev.teogor.ceres.framework.core.screen.isStatusBarVisible
import dev.teogor.ceres.framework.core.screen.isVisible
import dev.teogor.ceres.framework.core.screen.showNavBar
import dev.teogor.ceres.framework.core.screen.showSettingsButton
import dev.teogor.ceres.framework.core.screen.toolbarTitle
import dev.teogor.ceres.framework.core.screen.toolbarTokens
import dev.teogor.ceres.monetisation.admob.AdmobBanner
import dev.teogor.ceres.monetisation.admob.nativead.AdEvent
import dev.teogor.ceres.monetisation.admob.nativead.AdLoaderConfig
import dev.teogor.ceres.monetisation.admob.nativead.NativeAd
import dev.teogor.ceres.monetisation.admob.nativead.NativeAdConfig
import dev.teogor.ceres.monetisation.admob.nativead.NativeAdUi
import dev.teogor.ceres.monetisation.admob.nativead.RefreshableNativeAd
import dev.teogor.ceres.monetisation.admob.nativead.createBodyView
import dev.teogor.ceres.monetisation.admob.nativead.createCallToActionView
import dev.teogor.ceres.monetisation.admob.nativead.createHeadlineView
import dev.teogor.ceres.monetisation.admob.nativead.createIconView
import dev.teogor.ceres.monetisation.admob.nativead.rememberAdLoader
import dev.teogor.ceres.monetisation.admob.showInterstitialAd
import dev.teogor.ceres.monetisation.messaging.ConsentManager
import dev.teogor.ceres.monetisation.messaging.ConsentResult
import dev.teogor.ceres.screen.builder.compose.ColumnLayout
import dev.teogor.ceres.screen.builder.customView
import dev.teogor.ceres.screen.builder.header
import dev.teogor.ceres.screen.builder.simpleView
import dev.teogor.ceres.ui.designsystem.Text
import dev.teogor.ceres.ui.theme.MaterialTheme

// todo better way to configure this. perhaps use kotlin builder syntax
@Composable
internal fun HomeRoute(
  baseActions: BaseActions,
) {
  val state by remember { ConsentManager.state }
  val canRequestAds: Boolean = remember(state) {
    when (val consentResult = state) {
      is ConsentResult.ConsentFormAcquired -> consentResult.canRequestAds
      is ConsentResult.ConsentFormDismissed -> consentResult.canRequestAds
      else -> false
    }
  }

  baseActions.setScreenInfo {
    showNavBar {
      true
    }

    isStatusBarVisible {
      true
    }

    toolbarTokens {
      isVisible {
        true
      }

      toolbarTitle {
        "Home"
      }

      showSettingsButton {
        true
      }
    }

    floatingButton {
      isVisible {
        false
      }
    }
  }

  val context = LocalContext.current

  // You can also get the current Activity, if needed
  val activity = remember(context) {
    context as? Activity
  }

  if (canRequestAds) {
    HomeScreen(
      activity,
    )
  } else {
    ConsentManager.loadAndShowConsentFormIfRequired()
  }
}

@Composable
private fun HomeScreen(
  activity: Activity?,
) = ColumnLayout(
  hasScrollbarBackground = false,
  screenName = HomeScreenConfig,
) {
  header {
    "Ad Settings"
  }

  simpleView(
    title = "Reset Advertising Choices",
    subtitle = "Reset your advertising choices to manage your options.",
    clickable = {
      ConsentManager.resetConsent()
    },
  )

  header {
    "Ads Demo"
  }

  customView {
    AdmobBanner(modifier = Modifier.fillMaxWidth())
  }

  simpleView(
    title = "Show Interstitial",
    clickable = {
      showInterstitialAd(activity)
    },
  )

  customView {
    val adId = "ca-app-pub-3940256099942544/2247696110"

    var nativeAd by remember {
      mutableStateOf<NativeAd?>(null)
    }
    var adClicked by remember {
      mutableStateOf(false)
    }
    val adLoader = rememberAdLoader(
      config = AdLoaderConfig(adId),
      onAdEvent = { event ->
        if (event == AdEvent.AdClicked) {
          adClicked = true
        }
      },
    ) {
      nativeAd = it
    }
    if (!adClicked) {
      RefreshableNativeAd(
        adId = adId,
        adLoader = adLoader,
        refreshIntervalMillis = 30000L,
      )
      val nativeAdConfig = nativeAdConfig()
      NativeAd(
        modifier = Modifier
          .padding(horizontal = 10.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(20.dp),
          )
          .padding(horizontal = 6.dp, vertical = 10.dp),
        nativeAdConfig = nativeAdConfig,
        adContent = {
          NativeAdUi(nativeAdConfig)
        },
        nativeAd = nativeAd,
      )
    }
  }
}

@Composable
fun nativeAdConfig() = NativeAdConfig.Builder()
  .headlineView(
    createHeadlineView {
      Text(
        text = it,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontSize = 18.sp,
      )
    },
  )
  .bodyView(
    createBodyView {
      Text(
        text = it,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontSize = 10.sp,
        maxLines = 1,
      )
    },
  )
  .callToActionView(
    createCallToActionView {
      Text(
        text = it,
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 12.sp,
      )
    },
  )
  .iconView(
    createIconView {
    },
  )
  .build()
