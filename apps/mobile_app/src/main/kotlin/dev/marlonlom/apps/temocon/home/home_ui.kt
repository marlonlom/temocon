@file:OptIn(
  ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
  ExperimentalMaterial3Api::class
)

package dev.marlonlom.apps.temocon.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.temocon.R
import timber.log.Timber

@Composable
fun HomeScreen(
  windowSizeClass: WindowSizeClass,
  navigateToAboutScreenAction: () -> Unit,
  toggleDarkThemeAction: () -> Unit,
  modifier: Modifier = Modifier
) {
  Timber.d("windowSizeClass=$windowSizeClass")
  Scaffold(
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    topBar = {
      HomeTopBar(
        navigateToAboutScreenAction = navigateToAboutScreenAction,
        toggleDarkThemeAction = toggleDarkThemeAction,
        modifier = modifier
      )
    },
    content = { innerPadding ->
      LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {

      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
  navigateToAboutScreenAction: () -> Unit,
  toggleDarkThemeAction: () -> Unit,
  modifier: Modifier = Modifier
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        text = stringResource(R.string.app_name),
        modifier = modifier,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    },
    actions = {
      IconButton(onClick = { toggleDarkThemeAction() }) {
        Icon(
          imageVector = Icons.Rounded.LightMode,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary
        )
      }
      IconButton(onClick = { navigateToAboutScreenAction() }) {
        Icon(
          imageVector = Icons.Rounded.Info,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary
        )
      }
    }
  )
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
  HomeScreen(
    windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(360.dp, 480.dp)),
    navigateToAboutScreenAction = {
      Timber.i("navigateToAboutScreenAction > clicked")
    },
    toggleDarkThemeAction = {
      Timber.i("toggleDarkThemeAction > clicked")
    }
  )
}
