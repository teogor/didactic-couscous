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

package dev.teogor.ceres.monetisation.admob.nativead

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import dev.teogor.ceres.monetisation.admob.databinding.AdmobNativeBinding

@Composable
fun NativeAd(
  modifier: Modifier = Modifier,
  adHeadlineView: @Composable (String) -> Unit,
  adBodyView: @Composable (String) -> Unit,
  callToActionView: @Composable (String) -> Unit,
  adContent: @Composable () -> Unit,
  nativeAd: NativeAd?,
) {
  val (adHeadline, adHeadlineComposeView) = createAdComponent(
    initialValue = "",
    content = adHeadlineView,
  )
  val (adBody, adBodyComposeView) = createAdComponent(
    initialValue = "",
    content = adBodyView,
  )
  val (callToAction, callToActionComposeView) = createAdComponent(
    initialValue = "",
    content = callToActionView,
  )
  var adView by remember {
    mutableStateOf<NativeAdView?>(null)
  }

  AndroidViewBinding(
    factory = AdmobNativeBinding::inflate,
    modifier = modifier,
  ) {
    if (adView == null) {
      adView = root.also { adview ->
        adview.bodyView = adBodyComposeView
        adview.headlineView = adHeadlineComposeView
        adview.callToActionView = callToActionComposeView
      }
      composeView.setContent {
        adContent()
        Column(
          modifier = Modifier.fillMaxWidth(),
        ) {
          AndroidView(
            factory = {
              adHeadlineComposeView
            },
          )
          AndroidView(
            factory = {
              adBodyComposeView
            },
          )
          AndroidView(
            factory = {
              callToActionComposeView
            },
          )
        }
      }
    }
  }

  LaunchedEffect(nativeAd) {
    nativeAd?.let { nativeAd ->
      nativeAd.advertiser?.let {
      }
      nativeAd.body?.let { body ->
        adBody.value = body
      }
      nativeAd.callToAction?.let {
        callToAction.value = it
      }
      nativeAd.headline?.let { headline ->
        adHeadline.value = headline
      }
      nativeAd.icon?.let { icon ->
        // adIcon = icon.drawable
      }
      adView?.setNativeAd(nativeAd)
    }
  }
}
