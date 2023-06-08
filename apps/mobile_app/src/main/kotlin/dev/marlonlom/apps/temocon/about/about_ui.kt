/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.about

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboutScreen(
  navigateBackToHomeAction: () -> Unit
) {
  Text(text = "About")
}
