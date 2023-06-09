/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.utilities.temocon.core.TemperatureUnit
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
  onTemperatureValueValueChanged: (Double) -> Unit
) {
  val inputState = rememberTemperatureValueInputState(initialValue = uiState.value.temperatureValue)
  val currentOnTemperatureValueChanged by rememberUpdatedState(onTemperatureValueValueChanged)

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

@Composable
private fun ToggleTemperatureUnitButtons(
  uiState: State<HomeUiState>, saveSelectedUnitIndexAction: (Int) -> Unit
) {
  val currentSaveSelectedUnitIndexAction by rememberUpdatedState(saveSelectedUnitIndexAction)
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    Spacer(modifier = Modifier.weight(1f))
    val temperatureUnits = TemperatureUnit.values()
    temperatureUnits.forEachIndexed { index: Int, _: TemperatureUnit ->
      val unitSymbolTxt = stringArrayResource(id = R.array.home_temperature_unit_symbols)[index]
      TemperatureUnitOutlinedButton(
        index = index,
        uiState = uiState,
        currentSaveSelectedUnitIndexAction = currentSaveSelectedUnitIndexAction,
        temperatureUnits = temperatureUnits,
        unitSymbolText = unitSymbolTxt
      )
    }
    Spacer(modifier = Modifier.weight(1f))
  }
}

/**
 * Temperature outline button composable ui function.
 *
 * @author marlonlom
 */
@Composable
private fun TemperatureUnitOutlinedButton(
  index: Int,
  uiState: State<HomeUiState>,
  currentSaveSelectedUnitIndexAction: (Int) -> Unit,
  temperatureUnits: Array<TemperatureUnit>,
  unitSymbolText: String
) {
  val cornerRadius: Dp = 20.dp
  OutlinedButton(
    modifier = Modifier.getTemperatureUnitToggleButtonModifier(index, uiState.value.selectedTemperatureUnitIndex),
    onClick = {
      currentSaveSelectedUnitIndexAction(index)
    },
    shape = getRoundedCornerShapeByTemperatureUnit(index, cornerRadius, temperatureUnits),
    border = BorderStroke(
      1.dp, if (uiState.value.selectedTemperatureUnitIndex == index) {
        MaterialTheme.colorScheme.primary
      } else {
        Color.DarkGray.copy(alpha = 0.75f)
      }
    ),
    colors = if (uiState.value.selectedTemperatureUnitIndex == index) {
      ButtonDefaults.outlinedButtonColors(
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        contentColor = MaterialTheme.colorScheme.primary
      )
    } else {
      ButtonDefaults.outlinedButtonColors(
        containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.primary
      )
    }
  ) {
    Text(
      text = unitSymbolText,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Medium,
      modifier = Modifier.padding(horizontal = 8.dp)
    )
  }
}

private fun Modifier.getTemperatureUnitToggleButtonModifier(
  index: Int, selectedIndex: Int
): Modifier = when (index) {
  0 -> this
    .offset(0.dp, 0.dp)
    .zIndex(if (selectedIndex == 0) 1f else 0f)

  else -> this
    .offset((-1 * index).dp, 0.dp)
    .zIndex(if (selectedIndex == index) 1f else 0f)
}

@Composable
private fun getRoundedCornerShapeByTemperatureUnit(
  index: Int, cornerRadius: Dp, temperatureUnits: Array<TemperatureUnit>
): RoundedCornerShape = when (index) {
  0 -> RoundedCornerShape(
    topStart = cornerRadius, topEnd = 0.dp, bottomStart = cornerRadius, bottomEnd = 0.dp
  )

  temperatureUnits.size - 1 -> RoundedCornerShape(
    topStart = 0.dp, topEnd = cornerRadius, bottomStart = 0.dp, bottomEnd = cornerRadius
  )

  else -> RoundedCornerShape(
    topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
  )
}

@Composable
private fun TemperatureValueTextField(
  inputState: TemperatureValueInputState = rememberTemperatureValueInputState()
) {
  TextField(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    value = inputState.textValue,
    onValueChange = {
      inputState.updateValue(it)
    },
    colors = TextFieldDefaults.colors(
      focusedContainerColor = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      errorContainerColor = Color.Transparent
    ),
    textStyle = MaterialTheme.typography.displayMedium.copy(
      textAlign = TextAlign.End,
      color = MaterialTheme.colorScheme.inverseSurface
    ),
    isError = inputState.isInValidText,
    supportingText = {
      if (inputState.isInValidText) {
        Text(text = "Enter a valid temperature value.")
      }
    },
    singleLine = true,
    keyboardOptions = KeyboardOptions.Default.copy(
      autoCorrect = false,
      keyboardType = KeyboardType.Number,
      imeAction = ImeAction.Done
    )
  )
}

@Composable
fun rememberTemperatureValueInputState(
  initialValue: Double = Double.NaN
): TemperatureValueInputState = rememberSaveable(
  initialValue, saver = TemperatureValueInputState.Saver
) {
  val text = if (initialValue.isNaN()) "" else initialValue.toString()
  TemperatureValueInputState(text)
}

class TemperatureValueInputState(initialValue: String) {
  var textValue by mutableStateOf(initialValue)
    private set

  val isInValidText by derivedStateOf {
    textValue.isNotEmpty() && textValue.toDoubleOrNull() == null
  }

  fun updateValue(newValue: String) {
    textValue = newValue
  }

  companion object {
    val Saver: Saver<TemperatureValueInputState, *> = listSaver(save = {
      listOf(it.textValue)
    }, restore = {
      TemperatureValueInputState(it[0])
    })
  }
}
