/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.home.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.temocon.home.HomeUiState
import dev.marlonlom.apps.temocon.home.widgets.TemperatureValueTextField
import dev.marlonlom.apps.temocon.home.widgets.ToggleTemperatureUnitButtons
import dev.marlonlom.apps.temocon.home.widgets.rememberTemperatureValueInputState
import timber.log.Timber

/**
 * Home input slot composable ui class.
 *
 * @author marlonlom
 *
 * @param
 */
@Composable
fun HomeInputSlot(
  uiState: State<HomeUiState>,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  onTemperatureValueChanged: (Double) -> Unit
) {
  val inputState = rememberTemperatureValueInputState()
  val currentOnTemperatureValueChanged by rememberUpdatedState(onTemperatureValueChanged)

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(25.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    ToggleTemperatureUnitButtons(
      uiState = uiState, saveSelectedUnitIndexAction = saveSelectedUnitIndexAction
    )
    TemperatureValueTextField(
      inputState = inputState
    )
  }

  LaunchedEffect(inputState) {
    snapshotFlow { inputState.textValue }.collect { text: String ->
      if (text.toDoubleOrNull() == null) {
        Timber.e("[HomeInputSlot/LaunchedEffect] text=$text; text.toDoubleOrNull=${text.toDoubleOrNull()}")
        return@collect
      }
      currentOnTemperatureValueChanged(text.toDouble())
    }
  }
}
