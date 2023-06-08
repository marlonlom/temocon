/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon

import android.app.Application
import dev.marlonlom.apps.temocon.ui.logging.timber.ReleaseTree
import timber.log.Timber

class MainApp : Application() {
  override fun onCreate() {
    super.onCreate()
    this.setupTimber()
  }

  private fun setupTimber() {
    val tree = if (BuildConfig.DEBUG) Timber.DebugTree() else ReleaseTree()
    Timber.plant(tree)
  }
}
