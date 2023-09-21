package dev.teogor.ceres.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.teogor.ceres.ads.HomeBannerAd
import dev.teogor.ceres.ads.HomeInterstitialAd
import dev.teogor.ceres.ads.HomeRewardedAd
import dev.teogor.ceres.ads.HomeRewardedInterstitialAd
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  val homeInterstitialAd: HomeInterstitialAd,
  val homeRewardedInterstitialAd: HomeRewardedInterstitialAd,
  val homeRewardedAd: HomeRewardedAd,
  val homeBannerAd: HomeBannerAd,
) : ViewModel()
