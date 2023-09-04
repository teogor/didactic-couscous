package dev.teogor.ceres.screen.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.teogor.ceres.navigation.events.TrackScreenViewEvent
import dev.teogor.ceres.screen.core.scope.ScreenColumnScope
import dev.teogor.ceres.screen.core.scope.ScreenGridScope
import dev.teogor.ceres.screen.core.scope.ScreenListScope
import dev.teogor.ceres.ui.designsystem.scrollbar.ColumnScrollbar
import dev.teogor.ceres.ui.designsystem.scrollbar.LazyColumnScrollbar
import dev.teogor.ceres.ui.designsystem.scrollbar.LazyGridScrollbar
import dev.teogor.ceres.ui.designsystem.scrollbar.ScrollbarCustomization
import dev.teogor.ceres.ui.designsystem.scrollbar.ScrollbarIndicator
import dev.teogor.ceres.ui.theme.MaterialTheme

@Composable
fun LazyColumnScreen(
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
      bottomContent?.invoke()
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

@Composable
fun LazyGridScreen(
  columns: GridCells,
  hasScrollbar: Boolean = true,
  hasScrollbarBackground: Boolean = true,
  screenName: String? = null,
  indicatorContent: ((Int) -> String)? = null,
  contentPadding: PaddingValues = PaddingValues(0.dp),
  reverseLayout: Boolean = false,
  verticalArrangement: Arrangement.Vertical =
    if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
  horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
  content: ScreenGridScope.() -> Unit,
) {
  val state = rememberLazyGridState().attachScrollState()
  LazyGridScrollbar(
    scrollbarCustomization = ScrollbarCustomization(
      state = state,
    ).copy(
      enabled = hasScrollbar,
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
    columns = columns,
  ) {
    LazyVerticalGrid(
      state = state,
      columns = columns,
      contentPadding = contentPadding,
      reverseLayout = reverseLayout,
      verticalArrangement = verticalArrangement,
      horizontalArrangement = horizontalArrangement,
    ) {
      content()
    }
  }

  screenName?.let {
    TrackScreenViewEvent(screenName = it)
  }
}

@Composable
fun ColumnScreen(
  modifier: Modifier = Modifier,
  verticalArrangement: Arrangement.Vertical = Arrangement.Top,
  horizontalAlignment: Alignment.Horizontal = Alignment.Start,
  hasScrollbar: Boolean = true,
  hasScrollbarBackground: Boolean = true,
  screenName: String? = null,
  bottomContent: (@Composable () -> Unit)? = null,
  content: @Composable ScreenColumnScope.() -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxSize(),
  ) {
    Box(
      modifier = Modifier.weight(1f),
    ) {
      val state = rememberScrollState().attachScrollState()
      ColumnScrollbar(
        scrollbarCustomization = ScrollbarCustomization(
          state = state,
        ).copy(
          enabled = hasScrollbar,
          hasScrollbarBackground = hasScrollbarBackground,
        ),
      ) {
        Column(
          modifier = Modifier
            .verticalScroll(state)
            .fillMaxWidth(),
          verticalArrangement = verticalArrangement,
          horizontalAlignment = horizontalAlignment,
        ) {
          content()
        }
      }
    }
    bottomContent?.invoke()
  }

  screenName?.let {
    TrackScreenViewEvent(screenName = it)
  }
}

@Composable
fun FullScreen(
  screenName: String? = null,
  backgroundColor: Color = MaterialTheme.colorScheme.background,
  content: @Composable BoxScope.() -> Unit,
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color = backgroundColor)
      .statusBarsPadding(),
    content = content,
  )

  screenName?.let {
    TrackScreenViewEvent(screenName = it)
  }
}
