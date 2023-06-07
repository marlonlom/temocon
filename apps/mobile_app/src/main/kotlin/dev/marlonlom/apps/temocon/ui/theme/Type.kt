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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import dev.marlonlom.apps.temocon.R

private object BrandFont {

  private val OUTFIT_FONT = FontFamily(
    Font(R.font.outfit_regular),
    Font(R.font.outfit_medium, weight = FontWeight.Medium),
  )

  private val DEFAULT_TEXT_STYLE = TextStyle(
    fontFamily = OUTFIT_FONT, fontWeight = FontWeight.Normal, lineHeightStyle = LineHeightStyle(
      alignment = LineHeightStyle.Alignment.Center, trim = LineHeightStyle.Trim.None
    )
  )

  val appTypography = Typography(
    displayLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = (-0.25).sp
    ),
    displayMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = 45.sp, lineHeight = 52.sp, letterSpacing = 0.sp
    ),
    displaySmall = DEFAULT_TEXT_STYLE.copy(
      fontSize = 36.sp, lineHeight = 44.sp, letterSpacing = 0.sp
    ),
    headlineLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = 32.sp,
      lineHeight = 40.sp,
      letterSpacing = 0.sp,
      lineBreak = LineBreak.Heading,
    ),
    headlineMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = 28.sp,
      lineHeight = 36.sp,
      letterSpacing = 0.sp,
      lineBreak = LineBreak.Heading,
    ),
    headlineSmall = DEFAULT_TEXT_STYLE.copy(
      fontSize = 24.sp,
      lineHeight = 32.sp,
      letterSpacing = 0.sp,
      lineBreak = LineBreak.Heading,
    ),
    titleLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = 22.sp,
      lineHeight = 28.sp,
      letterSpacing = 0.sp,
      lineBreak = LineBreak.Heading,
    ),
    titleMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = 16.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.15.sp,
      lineBreak = LineBreak.Heading,
    ),
    titleSmall = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 14.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.1.sp,
      lineBreak = LineBreak.Heading,
    ),
    bodyLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = 16.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.5.sp,
      lineBreak = LineBreak.Heading,
    ),
    bodyMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = 14.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.25.sp,
      lineBreak = LineBreak.Heading,
    ),
    bodySmall = DEFAULT_TEXT_STYLE.copy(
      fontSize = 12.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.4.sp,
      lineBreak = LineBreak.Heading,
    ),
    labelLarge = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 14.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.1.sp,
    ),
    labelMedium = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 12.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.5.sp,
    ),
    labelSmall = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 11.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.5.sp,
    ),
  )
}

/* Set of Material typography styles to start with */
val typography = BrandFont.appTypography
