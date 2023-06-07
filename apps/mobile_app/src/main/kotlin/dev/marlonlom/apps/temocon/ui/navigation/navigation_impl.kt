package dev.marlonlom.apps.temocon.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.apps.temocon.about.AboutScreen
import dev.marlonlom.apps.temocon.home.HomeScreen
import timber.log.Timber

sealed class Destination(@StringRes val route: Int) {
  object Home : Destination(R.string.destination_home)
  object About : Destination(R.string.destination_about)
}

@Composable
fun AppNavHost(
  navController: NavHostController,
  windowSizeClass: WindowSizeClass,
  isSystemInDarkTheme: Boolean,
  selectedTemperatureUnitIndex: MutableIntState,
  defaultDestination: String = stringResource(id = Destination.Home.route)
) {
  val aboutRoute = stringResource(id = Destination.About.route)

  NavHost(
    navController = navController, startDestination = defaultDestination
  ) {

    composable(route = defaultDestination) {
      HomeScreen(windowSizeClass = windowSizeClass,
        isSystemInDarkTheme = isSystemInDarkTheme,
        selectedIndex = selectedTemperatureUnitIndex.intValue,
        navigateToAboutScreenAction = { navController.navigate(aboutRoute) },
        saveSelectedUnitIndexAction = { temperatureUnitIndex: Int ->
          selectedTemperatureUnitIndex.value = temperatureUnitIndex

        },
        toggleDarkThemeAction = { isDarkTheme ->
          Timber.d("[toggleDarkThemeAction] newIsDarkThemeFlag=$isDarkTheme")
        })
    }

    composable(route = aboutRoute) {
      AboutScreen(navigateBackToHomeAction = {
        navController.popBackStack()
      })
    }
  }
}

