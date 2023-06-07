package dev.marlonlom.apps.temocon.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  windowSizeClass: WindowSizeClass,
  selectedIndex: Int,
  navigateToAboutScreenAction: () -> Unit,
  toggleDarkThemeAction: (Boolean) -> Unit,
  saveSelectedUnitIndexAction: (Int) -> Unit,
  modifier: Modifier = Modifier,
  isSystemInDarkTheme: Boolean
) {
  Scaffold(
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    topBar = {
      HomeTopBar(
        isSystemInDarkTheme = isSystemInDarkTheme,
        windowSizeClass = windowSizeClass,
        navigateToAboutScreenAction = navigateToAboutScreenAction,
        toggleDarkThemeAction = toggleDarkThemeAction,
        modifier = modifier
      )
    },
    content = { innerPadding ->
      HomeScreenContent(
        innerPadding = innerPadding,
        windowSizeClass = windowSizeClass,
        selectedIndex = selectedIndex,
        saveSelectedUnitIndexAction = saveSelectedUnitIndexAction,
        modifier = modifier
      )
    }
  )
}

