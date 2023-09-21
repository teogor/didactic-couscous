package dev.teogor.ceres.monetisation.admob.formats

import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

sealed class CacheAdModel {
  data class AppOpen(val ad: AppOpenAd) : CacheAdModel()
  data class Interstitial(val ad: InterstitialAd) : CacheAdModel()
  data class RewardedInterstitial(val ad: RewardedInterstitialAd) : CacheAdModel()
  data class Rewarded(val ad: RewardedAd) : CacheAdModel()
  data class Native(val ad: NativeAd) : CacheAdModel()
}
