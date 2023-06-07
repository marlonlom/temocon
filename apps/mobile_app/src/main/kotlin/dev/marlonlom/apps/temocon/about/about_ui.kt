package dev.marlonlom.apps.temocon.about

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboutScreen(
  navigateBackToHomeAction: () -> Unit
) {
  Text(text = "About")
}
