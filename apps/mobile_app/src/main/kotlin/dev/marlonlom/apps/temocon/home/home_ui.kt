package dev.marlonlom.apps.temocon.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun HomeRoute(
  viewModel: HomeViewModel,
  windowSizeClass: WindowSizeClass,
  navigateToAboutScreenAction: () -> Unit,
  toggleDarkThemeAction: (Boolean) -> Unit,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()
  Timber.d("[HomeRoute] uiState: $uiState")
  Scaffold(
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    topBar = {
      HomeTopBar(
        uiState = uiState,
        windowSizeClass = windowSizeClass,
        navigateToAboutScreenAction = navigateToAboutScreenAction,
        toggleDarkThemeAction = toggleDarkThemeAction,
        modifier = modifier
      )
    },
    content = { innerPadding: PaddingValues ->
      HomeScreenContent(
        uiState = uiState,
        innerPadding = innerPadding,
        windowSizeClass = windowSizeClass,
        saveSelectedUnitIndexAction = saveSelectedUnitIndexAction,
        modifier = modifier
      )
    }
  )
}

@Composable
fun HomeScreenContent(
  uiState: State<HomeUiState>,
  innerPadding: PaddingValues,
  windowSizeClass: WindowSizeClass,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Timber.d("[HomeScreenContent] windowSizeClass=$windowSizeClass")
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(innerPadding),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    InputSlot(
      uiState = uiState,
      saveSelectedUnitIndexAction = saveSelectedUnitIndexAction,
      modifier = modifier
    )
  }
}
