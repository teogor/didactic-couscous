package dev.teogor.ceres.screen.builder.utilities

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T> T?.perform(
  onNull: () -> Unit = {},
  block: (T) -> Unit,
) {
  contract {
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
  if (this == null) {
    onNull()
  } else {
    block(requireNotNull(this))
  }
}

