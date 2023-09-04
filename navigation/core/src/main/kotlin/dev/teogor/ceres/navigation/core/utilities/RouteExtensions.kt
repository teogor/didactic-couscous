package dev.teogor.ceres.navigation.core.utilities

import dev.teogor.ceres.navigation.core.ScreenRoute
import java.util.Locale

fun ScreenRoute.toScreenName(): String {
  val route = route.removeSuffix("_route")
  val words = route.split("_")
  val capitalizedWords = words.map { it ->
    it.replaceFirstChar {
      if (it.isLowerCase()) {
        it.titlecase(Locale.getDefault())
      } else {
        it.toString()
      }
    }
  }
  return capitalizedWords.joinToString("")
}
