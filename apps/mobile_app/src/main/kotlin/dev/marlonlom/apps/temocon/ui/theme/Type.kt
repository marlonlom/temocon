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

  private val SIXTY_FOUR_SP = 64.sp
  private val FIFTY_SEVEN_SP = 57.sp
  private val FIFTY_TWO_SP = 52.sp
  private val FORTY_FIVE_SP = 45.sp
  private val FORTY_FOUR_SP = 44.sp
  private val FORTY_SP = 40.sp
  private val THIRTY_SIX_SP = 36.sp
  private val THIRTY_TWO_SP = 32.sp
  private val TWENTY_EIGHT_SP = 28.sp
  private val TWENTY_FOUR_SP = 24.sp
  private val TWENTY_TWO_SP = 22.sp
  private val TWENTY_SP = 20.sp
  private val SIXTEEN_SP = 16.sp
  private val FOURTEEN_SP = 14.sp
  private val TWELVE_SP = 12.sp
  private val ELEVEN_SP = 11.sp
  private val ZERO_POINT_FIVE_SP = 0.5.sp
  private val ZERO_POINT_FOUR_SP = 0.4.sp
  private val ZERO_POINT_TWENTY_FIVE_SP = 0.25.sp
  private val ZERO_POINT_FIFTEEN_SP = 0.15.sp
  private val ZERO_POINT_ONE_SP = 0.1.sp
  private val ZERO_SP = 0.sp
  private const val NEG_ZERO_POINT_TWENTY_FIVE = -0.25
  private val NEG_ZERO_POINT_TWENTY_FIVE_SP = NEG_ZERO_POINT_TWENTY_FIVE.sp

  val appTypography = Typography(
    displayLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = FIFTY_SEVEN_SP, lineHeight = SIXTY_FOUR_SP, letterSpacing = NEG_ZERO_POINT_TWENTY_FIVE_SP
    ),
    displayMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = FORTY_FIVE_SP, lineHeight = FIFTY_TWO_SP, letterSpacing = ZERO_SP
    ),
    displaySmall = DEFAULT_TEXT_STYLE.copy(
      fontSize = THIRTY_SIX_SP, lineHeight = FORTY_FOUR_SP, letterSpacing = ZERO_SP
    ),
    headlineLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = THIRTY_TWO_SP,
      lineHeight = FORTY_SP,
      letterSpacing = ZERO_SP,
      lineBreak = LineBreak.Heading,
    ),
    headlineMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = TWENTY_EIGHT_SP,
      lineHeight = THIRTY_SIX_SP,
      letterSpacing = ZERO_SP,
      lineBreak = LineBreak.Heading,
    ),
    headlineSmall = DEFAULT_TEXT_STYLE.copy(
      fontSize = TWENTY_FOUR_SP,
      lineHeight = THIRTY_TWO_SP,
      letterSpacing = ZERO_SP,
      lineBreak = LineBreak.Heading,
    ),
    titleLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = TWENTY_TWO_SP,
      lineHeight = TWENTY_EIGHT_SP,
      letterSpacing = ZERO_SP,
      lineBreak = LineBreak.Heading,
    ),
    titleMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = SIXTEEN_SP,
      lineHeight = TWENTY_FOUR_SP,
      letterSpacing = ZERO_POINT_FIFTEEN_SP,
      lineBreak = LineBreak.Heading,
    ),
    titleSmall = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = FOURTEEN_SP,
      lineHeight = TWENTY_SP,
      letterSpacing = ZERO_POINT_ONE_SP,
      lineBreak = LineBreak.Heading,
    ),
    bodyLarge = DEFAULT_TEXT_STYLE.copy(
      fontSize = SIXTEEN_SP,
      lineHeight = TWENTY_FOUR_SP,
      letterSpacing = ZERO_POINT_FIVE_SP,
      lineBreak = LineBreak.Heading,
    ),
    bodyMedium = DEFAULT_TEXT_STYLE.copy(
      fontSize = FOURTEEN_SP,
      lineHeight = TWENTY_SP,
      letterSpacing = ZERO_POINT_TWENTY_FIVE_SP,
      lineBreak = LineBreak.Heading,
    ),
    bodySmall = DEFAULT_TEXT_STYLE.copy(
      fontSize = TWELVE_SP,
      lineHeight = SIXTEEN_SP,
      letterSpacing = ZERO_POINT_FOUR_SP,
      lineBreak = LineBreak.Heading,
    ),
    labelLarge = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = FOURTEEN_SP,
      lineHeight = TWENTY_SP,
      letterSpacing = ZERO_POINT_ONE_SP,
    ),
    labelMedium = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = TWELVE_SP,
      lineHeight = SIXTEEN_SP,
      letterSpacing = ZERO_POINT_FIVE_SP,
    ),
    labelSmall = DEFAULT_TEXT_STYLE.copy(
      fontWeight = FontWeight.Medium,
      fontSize = ELEVEN_SP,
      lineHeight = SIXTEEN_SP,
      letterSpacing = ZERO_POINT_FIVE_SP,
    ),
  )
}

/* Set of Material typography styles to start with */
val typography = BrandFont.appTypography
