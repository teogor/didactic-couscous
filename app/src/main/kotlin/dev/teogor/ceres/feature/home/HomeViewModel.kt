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

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.teogor.ceres.ads.HomeBannerAd
import dev.teogor.ceres.ads.HomeInterstitialAd
import dev.teogor.ceres.ads.HomeRewardedAd
import dev.teogor.ceres.ads.HomeRewardedInterstitialAd
import dev.teogor.ceres.core.network.NetworkMonitor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  val homeInterstitialAd: HomeInterstitialAd,
  val homeRewardedInterstitialAd: HomeRewardedInterstitialAd,
  val homeRewardedAd: HomeRewardedAd,
  val homeBannerAd: HomeBannerAd,
) : ViewModel() {

  var nativeAd = mutableStateOf<NativeAd?>(null)
    private set

  fun setNativeAd(ad: NativeAd) {
    nativeAd.value = ad
  }

}
