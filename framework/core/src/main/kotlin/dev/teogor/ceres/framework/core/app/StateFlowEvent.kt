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

package dev.teogor.ceres.framework.core.app

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun <T> StateFlowEvent<T>.collectAsStateFlowEvent(): StateFlowEvent<T> {
  val lifecycleOwner = LocalLifecycleOwner.current
  val collectedState = remember { mutableStateOf(this.stateFlow.value) }

  DisposableEffect(this.stateFlow) {
    val observer: (T) -> Unit = { value ->
      collectedState.value = value
    }

    val job = this@collectAsStateFlowEvent.stateFlow
      .onEach { value -> observer(value) }
      .launchIn(lifecycleOwner.lifecycleScope)

    onDispose {
      job.cancel()
    }
  }

  return StateFlowEvent(collectedState.value)
}

class StateFlowEvent<T>(defaultValue: T) {
  private val _stateFlow = MutableStateFlow(defaultValue)
  val stateFlow: StateFlow<T> = _stateFlow

  fun setValue(newValue: T) {
    _stateFlow.value = newValue
  }
}

@Composable
inline fun <reified T> stateFlowEvent(
  defaultValue: T,
): StateFlowEvent<T> {
  val lifecycleOwner = LocalLifecycleOwner.current

  val stateFlowEvent = remember { StateFlowEvent(defaultValue) }

  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      if (event == Lifecycle.Event.ON_DESTROY) {
        stateFlowEvent.setValue(defaultValue)
      }
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }

  return stateFlowEvent
}

@Composable
inline fun <reified T> StateFlowEvent<T>.observeFrom(
  default: T,
  crossinline observer: (T) -> Unit = {},
): T {
  val lifecycleOwner = LocalLifecycleOwner.current

  DisposableEffect(this) {
    val observerFunction: (T) -> Unit = { value ->
      observer(value)
    }

    val job = this@observeFrom.stateFlow
      .onEach { value -> observerFunction(value) }
      .launchIn(lifecycleOwner.lifecycleScope)

    onDispose {
      job.cancel()
    }
  }

  DisposableEffect(lifecycleOwner) {
    val lifecycleObserver = LifecycleEventObserver { _, event ->
      if (event == Lifecycle.Event.ON_DESTROY) {
        this@observeFrom.setValue(default)
      }
    }
    lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
    onDispose {
      lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
    }
  }

  return this.stateFlow.collectAsState(initial = default).value
}

/**
 * A side-effect which records a screen view event.
 */
@Composable
fun UpdateToolbarAlpha(
  value: Float,
  ceresNavVM: CeresNavVM = LocalCeresNavVM.current,
) {
  ceresNavVM.updateToolbarAlpha(value)
}

@Composable
fun <T : ScrollableState> T.attachScrollState(trigger: Int = 50): T {
  val toolbarAlphaState = remember { mutableStateOf(0f) }

  LaunchedEffect(this) {
    snapshotFlow {
      when (val scrollState = this@attachScrollState) {
        is ScrollState -> scrollState.value
        is LazyListState -> {
          val firstVisibleItemScrollOffset = scrollState.firstVisibleItemScrollOffset
          val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
          if (firstVisibleItemIndex == 0) {
            firstVisibleItemScrollOffset
          } else {
            trigger
          }
        }

        is LazyGridState -> {
          val firstVisibleItemScrollOffset = scrollState.firstVisibleItemScrollOffset
          val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
          if (firstVisibleItemIndex == 0) {
            firstVisibleItemScrollOffset
          } else {
            trigger
          }
        }

        else -> 0
      }
    }.collect { value ->
      val newAlpha = if (value < trigger) value / trigger.toFloat() else 1f
      if (newAlpha != toolbarAlphaState.value) {
        toolbarAlphaState.value = newAlpha
      }
    }
  }

  UpdateToolbarAlpha(toolbarAlphaState.value)
  return this
}

@Composable
inline fun ToolbarBackground(
  modifier: Modifier = Modifier,
  paddingBottom: Dp = 0.dp,
  crossinline content: (@Composable () -> Unit),
) {
  val ceresNavVM = LocalCeresNavVM.current
  val toolbarColor by remember(ceresNavVM.toolbarColor.value) {
    mutableStateOf(ceresNavVM.toolbarColor.value)
  }
  Box(
    modifier = Modifier
      .background(toolbarColor)
      .padding(bottom = paddingBottom),
  ) {
    content()
  }
}
