package dev.marlonlom.apps.temocon.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.utilities.temocon.core.TemperatureUnit
import timber.log.Timber
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
  isSystemInDarkTheme: Boolean,
  navigateToAboutScreenAction: () -> Unit,
  toggleDarkThemeAction: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  CenterAlignedTopAppBar(title = {
    Text(
      text = stringResource(R.string.app_name),
      modifier = modifier,
      fontWeight = FontWeight.Medium,
      maxLines = 1,
      style = MaterialTheme.typography.headlineSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )
  }, actions = {
    IconButton(onClick = { toggleDarkThemeAction(!isSystemInDarkTheme) }) {
      ToggleDarkThemeIcon(isSystemInDarkTheme)
    }
    IconButton(onClick = { navigateToAboutScreenAction() }) {
      Icon(
        imageVector = Icons.Rounded.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary
      )
    }
  })
}

@Composable
private fun ToggleDarkThemeIcon(isSystemInDarkTheme: Boolean) {
  val iconImageVector = if (isSystemInDarkTheme) Icons.Rounded.LightMode else Icons.Rounded.DarkMode
  val iconContentDescription = stringResource(
    id = if (isSystemInDarkTheme) {
      R.string.home_toggle_theme_nondark_content_desc
    } else {
      R.string.home_toggle_theme_dark_content_desc
    }
  )
  Icon(
    imageVector = iconImageVector,
    contentDescription = iconContentDescription,
    tint = MaterialTheme.colorScheme.primary
  )
}

@Composable
internal fun HomeScreenContent(
  innerPadding: PaddingValues,
  selectedIndex: Int,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(innerPadding),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    InputSlot(
      selectedIndex = selectedIndex,
      saveSelectedUnitIndexAction = saveSelectedUnitIndexAction,
      modifier = modifier
    )
  }
}

@Composable
private fun InputSlot(
  selectedIndex: Int,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Column {
    ToggleTemperatureUnitButtons(
      selectedIndex = selectedIndex,
      saveSelectedUnitIndexAction = saveSelectedUnitIndexAction,
      modifier = modifier
    )
  }
}

@Composable
private fun ToggleTemperatureUnitButtons(
  selectedIndex: Int,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val cornerRadius = 20.dp
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    Spacer(modifier = Modifier.weight(1f))
    val temperatureUnits = TemperatureUnit.values()
    temperatureUnits.forEachIndexed { index: Int, temperatureUnit: TemperatureUnit ->
      val unitName = temperatureUnit.name.lowercase(Locale.getDefault())
      val unitSymbolTxt = stringArrayResource(id = R.array.home_temperature_unit_symbols)[index]
      OutlinedButton(
        modifier = when (index) {
          0 -> Modifier
            .offset(0.dp, 0.dp)
            .zIndex(if (selectedIndex == 0) 1f else 0f)

          else -> Modifier
            .offset((-1 * index).dp, 0.dp)
            .zIndex(if (selectedIndex == index) 1f else 0f)
        },
        onClick = {
          Timber.d("[ToggleTemperatureUnitButtons] Clicked on index $index for unit symbol [$unitSymbolTxt: $unitName]")
          saveSelectedUnitIndexAction(index)
        },
        shape = roundedCornerShapeByTemperatureUnit(index, cornerRadius, temperatureUnits),
        border = BorderStroke(
          1.dp,
          if (selectedIndex == index) {
            MaterialTheme.colorScheme.primary
          } else {
            Color.DarkGray.copy(alpha = 0.75f)
          }
        ),
        colors = if (selectedIndex == index) {
          ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.primary
          )
        } else {
          ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
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

@Composable
private fun roundedCornerShapeByTemperatureUnit(
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
