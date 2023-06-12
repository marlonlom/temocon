package dev.marlonlom.apps.temocon.home.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.temocon.R

@Composable
fun TemperatureValueTextField(
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
        Text(text = stringResource(R.string.home_input_value_error_invalid))
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
