/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
    installSplashScreen()
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

      val homeUiState = homeViewModel.homeState.collectAsState()
      val isAppInDarkTheme = homeUiState.value.isAppInDarkTheme

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
