package dev.marlonlom.apps.temocon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.marlonlom.apps.temocon.ui.theme.TemoconTheme

/**
 * Main Activity class.
 *
 * @author marlonlom
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TalculatorAppContent()
    }
  }
}

@Composable
private fun TalculatorAppContent() {
  TemoconTheme {
    // A surface container using the 'background' color from the theme
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.background,
    ) {
      Greeting("Android")
    }
  }
}

/**
 * Greeting screen composable function.
 *
 * @author marlonlom
 *
 * @param name a name for the greeting.
 */
@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

/**
 * Greeting screen preview composable function.
 *
 * @author marlonlom
 *
 */
@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun DefaultPreview() {
  TemoconTheme {
    Greeting("Android")
  }
}
