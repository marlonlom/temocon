package dev.marlonlom.apps.temocon.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.utilities.temocon.core.TemperatureUnit

@Composable
fun InputSlot(
  uiState: State<HomeUiState>, saveSelectedUnitIndexAction: (Int) -> Unit, modifier: Modifier = Modifier
) {
  Column {
    ToggleTemperatureUnitButtons(
      uiState = uiState, saveSelectedUnitIndexAction = saveSelectedUnitIndexAction, modifier = modifier
    )
  }
}

@Composable
private fun ToggleTemperatureUnitButtons(
  uiState: State<HomeUiState>,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val currentSaveSelectedUnitIndexAction by rememberUpdatedState(saveSelectedUnitIndexAction)
  val cornerRadius = 20.dp
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    Spacer(modifier = Modifier.weight(1f))
    val temperatureUnits = TemperatureUnit.values()
    temperatureUnits.forEachIndexed { index: Int, _: TemperatureUnit ->
      val unitSymbolTxt = stringArrayResource(id = R.array.home_temperature_unit_symbols)[index]

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
          text = unitSymbolTxt,
          color = MaterialTheme.colorScheme.onSurface,
          fontWeight = FontWeight.Medium,
          modifier = Modifier.padding(horizontal = 8.dp)
        )
      }

    }
    Spacer(modifier = Modifier.weight(1f))
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
