package dev.teogor.ceres.monetisation.admob

import android.util.Log

interface Logger {
  val tag: String
    get() = this::class.java.simpleName

  fun log(content: String) {
    Log.d(tag, content)
  }
}
