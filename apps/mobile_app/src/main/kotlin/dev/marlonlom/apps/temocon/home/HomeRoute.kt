/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.home

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
import dev.marlonlom.apps.temocon.home.slots.HomeInputSlot
import dev.marlonlom.apps.temocon.home.slots.HomeOutputSlot
import dev.marlonlom.apps.temocon.home.widgets.HomeTopBar
import dev.marlonlom.utilities.temocon.core.TemperatureConvertResponse
import timber.log.Timber

/**
 * Application home route composable ui class.
 *
 * @author marlonlom
 */
@Composable
fun HomeRoute(
  viewModel: HomeViewModel,
  params: HomeRouteParams
) {
  val inputUiState = viewModel.homeState.collectAsStateWithLifecycle()
  val outputUiState = viewModel.responseState.collectAsStateWithLifecycle()
  Timber.d("[HomeRoute] state{inputs=$inputUiState, outputs=$outputUiState}")
  Scaffold(
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    topBar = {
      HomeTopBar(
        uiState = inputUiState,
        windowSizeClass = params.windowSizeClass,
        navigateToAboutScreenAction = params.navigateToAboutScreenAction,
        toggleDarkThemeAction = params.toggleDarkThemeAction
      )
    },
    content = { innerPadding: PaddingValues ->
      HomeScreenContent(
        inputsUiState = inputUiState,
        outputsUiState = outputUiState,
        innerPadding = innerPadding,
        windowSizeClass = params.windowSizeClass,
        saveSelectedUnitIndexAction = params.saveSelectedUnitIndexAction,
        onTemperatureValueChanged = params.onTemperatureValueChanged
      )
    })
}

/**
 * Application home route parameters data class.
 *
 * @author marlonlom
 */
data class HomeRouteParams(
  val windowSizeClass: WindowSizeClass,
  val navigateToAboutScreenAction: () -> Unit,
  val toggleDarkThemeAction: (Boolean) -> Unit,
  val saveSelectedUnitIndexAction: (Int) -> Unit,
  val onTemperatureValueChanged: (Double) -> Unit
)

/**
 * Application home route inner content composable ui class.
 *
 * @author marlonlom
 */
@Composable
fun HomeScreenContent(
  inputsUiState: State<HomeUiState>,
  outputsUiState: State<TemperatureConvertResponse>,
  innerPadding: PaddingValues,
  windowSizeClass: WindowSizeClass,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  onTemperatureValueChanged: (Double) -> Unit
) {
  Timber.d("[HomeScreenContent] windowSizeClass=$windowSizeClass")
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(innerPadding), verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    HomeInputSlot(
      uiState = inputsUiState,
      saveSelectedUnitIndexAction = saveSelectedUnitIndexAction,
      onTemperatureValueChanged = onTemperatureValueChanged
    )
    HomeOutputSlot(
      uiState = outputsUiState
    )
  }
}
