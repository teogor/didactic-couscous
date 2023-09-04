package dev.teogor.ceres.data.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.Flow

@Composable
inline fun <reified T> rememberPreference(
  preferenceFlow: Flow<T>,
  initialValue: T,
): T {
  val preferenceState = remember { mutableStateOf(initialValue) }

  LaunchedEffect(preferenceFlow) {
    preferenceFlow.collect { newValue ->
      preferenceState.value = newValue
    }
  }

  return preferenceState.value
}
