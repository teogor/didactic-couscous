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

package dev.teogor.ceres.monetisation.admob

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
  AndroidView(
    modifier = modifier.fillMaxWidth(),
    factory = { context ->
      // on below line specifying ad view.
      AdView(context).apply {
        // on below line specifying ad size
        // adSize = AdSize.BANNER
        // on below line specifying ad unit id
        // currently added a test ad unit id.
        setAdSize(AdSize.BANNER)
        adUnitId = "ca-app-pub-3940256099942544/6300978111"
        // calling load ad to load our ad.
        loadAd(AdRequest.Builder().build())
      }
    },
  )
}

fun showInterstitialAd(
  activity: Activity?,
) {
  activity?.let { activity ->
    InterstitialAd.load(
      activity,
      "ca-app-pub-3940256099942544/1033173712", // Change this with your own AdUnitID!
      AdRequest.Builder().build(),
      object : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad(adError: LoadAdError) {
        }

        override fun onAdLoaded(interstitialAd: InterstitialAd) {
          interstitialAd.show(activity)
        }
      },
    )
  }
}
