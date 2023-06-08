package dev.marlonlom.apps.temocon.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.apps.temocon.about.AboutScreen
import dev.marlonlom.apps.temocon.home.HomeRoute
import dev.marlonlom.apps.temocon.home.HomeViewModel

sealed class Destination(@StringRes val route: Int) {
  object Home : Destination(R.string.destination_home)
  object About : Destination(R.string.destination_about)
}

@Composable
fun AppNavHost(
  navController: NavHostController,
  windowSizeClass: WindowSizeClass,
  defaultDestination: String = stringResource(id = Destination.Home.route),
  homeViewModel: HomeViewModel
) {
  val aboutRoute = stringResource(id = Destination.About.route)

  NavHost(
    navController = navController, startDestination = defaultDestination
  ) {

    composable(route = defaultDestination) {
      HomeRoute(
        viewModel = homeViewModel,
        windowSizeClass = windowSizeClass,
        navigateToAboutScreenAction = {
          navController.navigate(aboutRoute)
        },
        toggleDarkThemeAction = { isDarkTheme ->
          homeViewModel.toggleIsAppInDarkThemeFlag(isDarkTheme)
        },
        saveSelectedUnitIndexAction = { index: Int ->
          homeViewModel.updateSelectedTemperatureUnitIndex(index)
        }
      )
    }

    composable(route = aboutRoute) {
      AboutScreen(navigateBackToHomeAction = {
        navController.popBackStack()
      })
    }
  }
}

