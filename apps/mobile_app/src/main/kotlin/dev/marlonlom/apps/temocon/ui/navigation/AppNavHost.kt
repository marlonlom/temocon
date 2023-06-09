/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.apps.temocon.about.AboutRoute
import dev.marlonlom.apps.temocon.home.HomeRoute
import dev.marlonlom.apps.temocon.home.HomeViewModel

/**
 * Application navigation host class.
 *
 * @author marlonlom
 *
 */
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
      AboutRoute(navigateBackToHomeAction = {
        navController.popBackStack()
      })
    }
  }
}


/**
 * Application navigation destination sealed class.
 *
 * @author marlonlom
 *
 */
sealed class Destination(@StringRes val route: Int) {

  /**
   * Application home destination object instance.
   *
   * @author marlonlom
   */
  object Home : Destination(R.string.destination_home)

  /**
   * Application about destination object instance.
   *
   * @author marlonlom
   */
  object About : Destination(R.string.destination_about)
}
