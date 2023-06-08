package dev.marlonlom.apps.temocon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.apps.temocon.home.HomeViewModel
import dev.marlonlom.apps.temocon.ui.navigation.AppNavHost
import dev.marlonlom.apps.temocon.ui.preferences.PreferencesStoreImpl
import dev.marlonlom.apps.temocon.ui.theme.TemoconTheme

/**
 * Main Activity class.
 *
 * @author marlonlom
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val windowSizeClass = calculateWindowSizeClass(this)
      val navController = rememberNavController()
      val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.provideFactory(
          preferencesStore = PreferencesStoreImpl(
            context = LocalContext.current
          )
        )
      )

      val homeUiState = homeViewModel.uiState.collectAsState()
      val isAppInDarkTheme = homeUiState.value.isAppInDarkTheme

      handleDarkThemeFromSystem(
        homeViewModel,
        isSystemInDarkTheme = isSystemInDarkTheme(),
        isAppInDarkTheme = isAppInDarkTheme
      )

      AppContent(
        navController,
        windowSizeClass,
        homeViewModel,
        isAppInDarkTheme
      )
    }
  }
}

@Composable
private fun AppContent(
  navController: NavHostController,
  windowSizeClass: WindowSizeClass,
  homeViewModel: HomeViewModel,
  isAppInDarkTheme: Boolean
) {
  TemoconTheme(darkTheme = isAppInDarkTheme) {
    AppNavHost(
      navController = navController,
      windowSizeClass = windowSizeClass,
      homeViewModel = homeViewModel
    )
  }
}

private fun handleDarkThemeFromSystem(
  homeViewModel: HomeViewModel,
  isSystemInDarkTheme: Boolean,
  isAppInDarkTheme: Boolean
) {
  val isSystemInDarkThemeButAppIsNot = isSystemInDarkTheme && !isAppInDarkTheme
  if (isSystemInDarkThemeButAppIsNot) {
    homeViewModel.toggleIsAppInDarkThemeFlag(isDarkTheme = true)
  }
}
