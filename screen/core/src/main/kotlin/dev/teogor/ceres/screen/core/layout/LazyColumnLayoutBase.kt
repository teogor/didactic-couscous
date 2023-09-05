package dev.teogor.ceres.screen.core.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.navigation.events.TrackScreenViewEvent
import dev.teogor.ceres.screen.core.attachScrollState
import dev.teogor.ceres.screen.core.scope.ScreenListScope
import dev.teogor.ceres.ui.designsystem.scrollbar.LazyColumnScrollbar
import dev.teogor.ceres.ui.designsystem.scrollbar.ScrollbarCustomization
import dev.teogor.ceres.ui.designsystem.scrollbar.ScrollbarIndicator

@Composable
fun LazyColumnLayoutBase(
  contentPadding: PaddingValues = PaddingValues(0.dp),
  verticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(0.dp),
  hasScrollbar: Boolean = true,
  hasScrollbarBackground: Boolean = true,
  screenName: String? = null,
  indicatorContent: ((Int) -> String)? = null,
  header: (@Composable () -> Unit)? = null,
  bottomContent: (@Composable () -> Unit)? = null,
  content: ScreenListScope.() -> Unit,
) {
  val state = rememberLazyListState().attachScrollState()

  if (header != null) {
    Column(
      modifier = Modifier,
    ) {
      header()
      LazyColumnScrollbar(
        scrollbarCustomization = ScrollbarCustomization(
          state = state,
        ).copy(
          enabled = hasScrollbar && !(!state.canScrollForward && !state.canScrollBackward),
          hasScrollbarBackground = hasScrollbarBackground,
          indicatorContent = if (indicatorContent != null) {
            { index, isThumbSelected ->
              if (isThumbSelected) {
                ScrollbarIndicator(
                  indicatorContent(index),
                )
              }
            }
          } else {
            null
          },
        ),
      ) {
        LazyColumn(
          state = state,
          contentPadding = contentPadding,
          verticalArrangement = verticalArrangement,
        ) {
          content()
        }
      }
    }
  } else if (bottomContent != null) {
    Column(
      modifier = Modifier.fillMaxSize(),
    ) {
      Box(
        modifier = Modifier.weight(1f),
      ) {
        LazyColumnScrollbar(
          scrollbarCustomization = ScrollbarCustomization(
            state = state,
          ).copy(
            enabled = hasScrollbar && !(!state.canScrollForward && !state.canScrollBackward),
            hasScrollbarBackground = hasScrollbarBackground,
            indicatorContent = if (indicatorContent != null) {
              { index, isThumbSelected ->
                if (isThumbSelected) {
                  ScrollbarIndicator(
                    indicatorContent(index),
                  )
                }
              }
            } else {
              null
            },
          ),
        ) {
          LazyColumn(
            state = state,
            contentPadding = contentPadding,
          ) {
            content()
          }
        }
      }
      bottomContent.invoke()
    }
  } else {
    LazyColumnScrollbar(
      scrollbarCustomization = ScrollbarCustomization(
        state = state,
      ).copy(
        enabled = hasScrollbar && !(!state.canScrollForward && !state.canScrollBackward),
        hasScrollbarBackground = hasScrollbarBackground,
        indicatorContent = if (indicatorContent != null) {
          { index, isThumbSelected ->
            if (isThumbSelected) {
              ScrollbarIndicator(
                indicatorContent(index),
              )
            }
          }
        } else {
          null
        },
      ),
    ) {
      LazyColumn(
        state = state,
        contentPadding = contentPadding,
      ) {
        content()
      }
    }
  }

  screenName?.let {
    TrackScreenViewEvent(screenName = it)
  }
}
