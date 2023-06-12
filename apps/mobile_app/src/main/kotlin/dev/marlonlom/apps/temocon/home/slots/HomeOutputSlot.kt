package dev.marlonlom.apps.temocon.home.slots

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.utilities.temocon.core.TemperatureConvertResponse

/**
 * Home output slot composable ui class.
 *
 * @author marlonlom
 *
 * @param
 */
@Composable
fun HomeOutputSlot(
  uiState: State<TemperatureConvertResponse>
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(20.dp)
      .clip(RoundedCornerShape(20.dp))
      .background(MaterialTheme.colorScheme.surfaceVariant),
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      text = "Celsius -> ${uiState.value.celsiusValue}",
      textAlign = TextAlign.Center
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      text = "Fahrenheit -> ${uiState.value.fahrenheitValue}",
      textAlign = TextAlign.Center
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      text = "Kelvin -> ${uiState.value.kelvinValue}",
      textAlign = TextAlign.Center
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      text = "Rankine -> ${uiState.value.rankineValue}",
      textAlign = TextAlign.Center
    )
  }

}
