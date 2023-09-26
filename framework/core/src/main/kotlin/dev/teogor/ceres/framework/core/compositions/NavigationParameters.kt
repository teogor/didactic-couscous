package dev.teogor.ceres.framework.core.compositions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

class NetworkConnectivity {
  var isOffline: Boolean by mutableStateOf(false)
}

val LocalNetworkConnectivity = staticCompositionLocalOf { NetworkConnectivity() }
