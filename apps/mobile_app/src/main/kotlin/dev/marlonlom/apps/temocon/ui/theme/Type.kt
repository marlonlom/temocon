/*
 * Copyright 2023 Marlonlom
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 *
*/
package dev.marlonlom.apps.temocon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.marlonlom.apps.temocon.R

val barlowCondensedFontFamily = FontFamily(
  Font(R.font.barlow_condensed_regular),
  Font(R.font.barlow_condensed_italic, style = FontStyle.Italic),
  Font(R.font.barlow_condensed_medium, weight = FontWeight.Medium),
  Font(R.font.barlow_condensed_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic)
)

// Set of Material typography styles to start with
val typography = Typography(
  bodyLarge = TextStyle(
    fontFamily = barlowCondensedFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp
  ),
  /* Other default text styles to override
  titleLarge = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Normal,
      fontSize = 22.sp,
      lineHeight = 28.sp,
      letterSpacing = 0.sp
  ),
  labelSmall = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Medium,
      fontSize = 11.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.5.sp
  )
  */
)
