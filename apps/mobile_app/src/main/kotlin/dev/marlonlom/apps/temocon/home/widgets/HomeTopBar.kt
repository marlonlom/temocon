/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.home.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DarkMode
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.marlonlom.apps.temocon.R
import dev.marlonlom.apps.temocon.home.HomeUiState


/**
 * Application home top bar composable ui class.
 *
 * @author marlonlom
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
  uiState: State<HomeUiState>,
  windowSizeClass: WindowSizeClass,
  navigateToAboutScreenAction: () -> Unit,
  toggleDarkThemeAction: (Boolean) -> Unit
) {
  val canShowAboutButton = canShowAboutButtonInTopBar(windowSizeClass)
  CenterAlignedTopAppBar(
    title = {
      Text(
        text = stringResource(R.string.app_name),
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    },
    actions = {
      val systemInDarkTheme = uiState.value.isAppInDarkTheme
      IconButton(
        onClick = {
          toggleDarkThemeAction(!systemInDarkTheme)
        },
      ) {
        ToggleDarkThemeIcon(systemInDarkTheme)
      }

      if (canShowAboutButton) {
        IconButton(onClick = { navigateToAboutScreenAction() }) {
          Icon(
            imageVector = Icons.TwoTone.Info,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
          )
        }
      }
    },
  )
}

/**
 * Returns true/false if the show about button should appear in the top bar.
 *
 * @param windowSizeClass window size class object instance.
 *
 * @return true/false if the show about button should appear in the top bar.
 */
fun canShowAboutButtonInTopBar(windowSizeClass: WindowSizeClass): Boolean {
  val isCompactWidth =
    arrayOf(WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium).contains(windowSizeClass.widthSizeClass)
  val isCompactOrMediumHeight = arrayOf(
    WindowHeightSizeClass.Compact,
    WindowHeightSizeClass.Medium,
    WindowHeightSizeClass.Expanded
  ).contains(windowSizeClass.heightSizeClass)

  return isCompactWidth && isCompactOrMediumHeight
}

@Composable
private fun ToggleDarkThemeIcon(isSystemInDarkTheme: Boolean) {
  val iconImageVector = if (isSystemInDarkTheme) Icons.TwoTone.LightMode else Icons.TwoTone.DarkMode
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
