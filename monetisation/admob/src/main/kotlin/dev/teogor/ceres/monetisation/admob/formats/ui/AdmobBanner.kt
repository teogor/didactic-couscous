package dev.teogor.ceres.monetisation.admob.formats.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import dev.teogor.ceres.monetisation.admob.formats.BannerAd

@Composable
fun AdmobBanner(
  modifier: Modifier = Modifier,
  bannerAd: BannerAd,
) {
  LaunchedEffect(bannerAd.adEvent.value) {
    Log.d("AdmobBanner", "event=${bannerAd.adEvent.value}")
  }

  AndroidView(
    modifier = modifier.fillMaxWidth(),
    factory = { context ->
      // on below line specifying ad view.
      bannerAd.buildAdView(context)
    },
  )
}
