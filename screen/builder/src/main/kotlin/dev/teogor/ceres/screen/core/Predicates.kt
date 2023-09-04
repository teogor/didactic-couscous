package dev.teogor.ceres.screen.core

import dev.teogor.ceres.screen.core.scope.ScreenColumnScope
import dev.teogor.ceres.screen.core.scope.ScreenGridScope
import dev.teogor.ceres.screen.core.scope.ScreenListScope


fun ScreenListScope.condition(
  predicate: () -> Boolean,
  action: ScreenListScope.() -> Unit,
) {
  if (predicate()) {
    action()
  }
}

fun ScreenGridScope.condition(
  predicate: () -> Boolean,
  action: ScreenGridScope.() -> Unit,
) {
  if (predicate()) {
    action()
  }
}

fun ScreenColumnScope.condition(
  predicate: () -> Boolean,
  action: ScreenColumnScope.() -> Unit,
) {
  if (predicate()) {
    action()
  }
}
