package dev.teogor.ceres.monetisation.admob.formats

import dev.teogor.ceres.core.runtime.AppMetadataManager
import dev.teogor.ceres.monetisation.admob.DemoAdUnitIds

abstract class AdId {
  abstract fun id(): String
  abstract fun type(): AdType

  val id: String = ""
    get() = if (!AppMetadataManager.isDebuggable) {
      id()
    } else when (type()) {
      AdType.AppOpen -> DemoAdUnitIds.APP_OPEN
      AdType.Interstitial -> DemoAdUnitIds.INTERSTITIAL
      AdType.RewardedInterstitial -> DemoAdUnitIds.REWARDED_INTERSTITIAL
      AdType.Native -> DemoAdUnitIds.NATIVE
      AdType.Rewarded -> DemoAdUnitIds.REWARDED
      AdType.Banner -> DemoAdUnitIds.BANNER
      AdType.InterstitialVideo -> DemoAdUnitIds.INTERSTITIAL_VIDEO
      AdType.NativeVideo -> DemoAdUnitIds.NATIVE_VIDEO
      AdType.Unspecified -> field
    }
}
