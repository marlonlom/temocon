/*
 * Copyright 2023 Marlonlom
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 *
*/
package dev.marlonlom.apps.temocon.ui.theme

import androidx.compose.ui.graphics.Color
import dev.marlonlom.apps.temocon.R

/* Brand colors */
private object BrandColors {
  val alice_blue = Color(R.color.brand_alice_blue)
  val alice_blue_2 = Color(R.color.brand_alice_blue_2)
  val arsenic = Color(R.color.brand_arsenic)
  val black = Color(R.color.brand_black)
  val blue_lagoon = Color(R.color.brand_blue_lagoon)
  val blue_lagoon_2 = Color(R.color.brand_blue_lagoon_2)
  val blue_lagoon_3 = Color(R.color.brand_blue_lagoon_3)
  val blue_lagoon_4 = Color(R.color.brand_blue_lagoon_4)
  val blue_lagoon_5 = Color(R.color.brand_blue_lagoon_5)
  val blue_lagoon_6 = Color(R.color.brand_blue_lagoon_6)
  val columbia_blue = Color(R.color.brand_columbia_blue)
  val columbia_blue_2 = Color(R.color.brand_columbia_blue_2)
  val columbia_blue_3 = Color(R.color.brand_columbia_blue_3)
  val columbia_blue_4 = Color(R.color.brand_columbia_blue_4)
  val columbia_blue_5 = Color(R.color.brand_columbia_blue_5)
  val columbia_blue_6 = Color(R.color.brand_columbia_blue_6)
  val columbia_blue_7 = Color(R.color.brand_columbia_blue_7)
  val dark_green = Color(R.color.brand_dark_green)
  val dark_green_2 = Color(R.color.brand_dark_green_2)
  val dark_green_3 = Color(R.color.brand_dark_green_3)
  val dark_green_4 = Color(R.color.brand_dark_green_4)
  val fire_brick = Color(R.color.brand_fire_brick)
  val maroon = Color(R.color.brand_maroon)
  val maroon_2 = Color(R.color.brand_maroon_2)
  val maya_blue = Color(R.color.brand_maya_blue)
  val maya_blue_2 = Color(R.color.brand_maya_blue_2)
  val maya_blue_3 = Color(R.color.brand_maya_blue_3)
  val maya_blue_4 = Color(R.color.brand_maya_blue_4)
  val melon = Color(R.color.brand_melon)
  val misty_rose = Color(R.color.brand_misty_rose)
  val mosque = Color(R.color.brand_mosque)
  val mosque_2 = Color(R.color.brand_mosque_2)
  val raven = Color(R.color.brand_raven)
  val sangria = Color(R.color.brand_sangria)
  val sherpa_blue = Color(R.color.brand_sherpa_blue)
  val sherpa_blue_2 = Color(R.color.brand_sherpa_blue_2)
  val sherpa_blue_3 = Color(R.color.brand_sherpa_blue_3)
  val sherpa_blue_4 = Color(R.color.brand_sherpa_blue_4)
  val solitude = Color(R.color.brand_solitude)
  val submarine = Color(R.color.brand_submarine)
  val white = Color(R.color.brand_white)
  val zumthor = Color(R.color.brand_zumthor)
}

/* Light theme colors */
val md_theme_light_primary = BrandColors.blue_lagoon
val md_theme_light_onPrimary = BrandColors.white
val md_theme_light_primaryContainer = BrandColors.columbia_blue
val md_theme_light_onPrimaryContainer = BrandColors.dark_green
val md_theme_light_secondary = BrandColors.blue_lagoon_2
val md_theme_light_onSecondary = BrandColors.white
val md_theme_light_secondaryContainer = BrandColors.columbia_blue_2
val md_theme_light_onSecondaryContainer = BrandColors.dark_green_2
val md_theme_light_tertiary = BrandColors.blue_lagoon_3
val md_theme_light_onTertiary = BrandColors.white
val md_theme_light_tertiaryContainer = BrandColors.columbia_blue_3
val md_theme_light_onTertiaryContainer = BrandColors.dark_green_3
val md_theme_light_error = BrandColors.fire_brick
val md_theme_light_errorContainer = BrandColors.misty_rose
val md_theme_light_onError = BrandColors.white
val md_theme_light_onErrorContainer = BrandColors.maroon
val md_theme_light_background = BrandColors.alice_blue
val md_theme_light_onBackground = BrandColors.dark_green_4
val md_theme_light_surface = BrandColors.alice_blue
val md_theme_light_onSurface = BrandColors.dark_green_4
val md_theme_light_surfaceVariant = BrandColors.solitude
val md_theme_light_onSurfaceVariant = BrandColors.arsenic
val md_theme_light_outline = BrandColors.raven
val md_theme_light_inverseOnSurface = BrandColors.alice_blue_2
val md_theme_light_inverseSurface = BrandColors.sherpa_blue
val md_theme_light_inversePrimary = BrandColors.maya_blue
/* TODO: check if this attribute works -> val md_theme_light_shadow = BrandColors.black */
val md_theme_light_surfaceTint = BrandColors.blue_lagoon_4
val md_theme_light_outlineVariant = BrandColors.zumthor
val md_theme_light_scrim = BrandColors.black

/* Dark theme colors */
val md_theme_dark_primary = BrandColors.maya_blue
val md_theme_dark_onPrimary = BrandColors.sherpa_blue_2
val md_theme_dark_primaryContainer = BrandColors.mosque
val md_theme_dark_onPrimaryContainer = BrandColors.columbia_blue_4
val md_theme_dark_secondary = BrandColors.maya_blue_2
val md_theme_dark_onSecondary = BrandColors.sherpa_blue_3
val md_theme_dark_secondaryContainer = BrandColors.mosque_2
val md_theme_dark_onSecondaryContainer = BrandColors.columbia_blue_5
val md_theme_dark_tertiary = BrandColors.maya_blue_3
val md_theme_dark_onTertiary = BrandColors.sherpa_blue_4
val md_theme_dark_tertiaryContainer = BrandColors.blue_lagoon_5
val md_theme_dark_onTertiaryContainer = BrandColors.columbia_blue_6
val md_theme_dark_error = BrandColors.melon
val md_theme_dark_errorContainer = BrandColors.sangria
val md_theme_dark_onError = BrandColors.maroon_2
val md_theme_dark_onErrorContainer = BrandColors.misty_rose
val md_theme_dark_background = BrandColors.dark_green_4
val md_theme_dark_onBackground = BrandColors.columbia_blue_7
val md_theme_dark_surface = BrandColors.dark_green_4
val md_theme_dark_onSurface = BrandColors.columbia_blue_7
val md_theme_dark_surfaceVariant = BrandColors.arsenic
val md_theme_dark_onSurfaceVariant = BrandColors.zumthor
val md_theme_dark_outline = BrandColors.submarine
val md_theme_dark_inverseOnSurface = BrandColors.dark_green_4
val md_theme_dark_inverseSurface = BrandColors.columbia_blue_7
val md_theme_dark_inversePrimary = BrandColors.blue_lagoon_6
/* TODO: check if this attribute works -> val md_theme_dark_shadow = BrandColors.black*/
val md_theme_dark_surfaceTint = BrandColors.maya_blue_4
val md_theme_dark_outlineVariant = BrandColors.arsenic
val md_theme_dark_scrim = BrandColors.black
