package dev.marlonlom.apps.temocon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.apps.temocon.ui.navigation.AppNavHost
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
      AppContent(navController, windowSizeClass)
    }
  }
}

@Composable
private fun AppContent(
  navController: NavHostController,
  windowSizeClass: WindowSizeClass
) {
  val selectedTemperatureUnitIndexState = rememberSaveable {
    mutableStateOf(0)
  }
  TemoconTheme {
    AppNavHost(
      navController = navController,
      windowSizeClass = windowSizeClass,
      selectedTemperatureUnitIndex = selectedTemperatureUnitIndexState,
      isSystemInDarkTheme = isSystemInDarkTheme(),
    )
  }
}
