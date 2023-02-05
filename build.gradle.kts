/*
 * Copyright 2023 Marlonlom
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 *
*/


/* Top-level build file where you can add configuration options common to all sub-projects/modules. */
plugins {
  id("com.android.application") version "7.4.0" apply false
  id("com.android.library") version "7.4.0" apply false
  id("org.jetbrains.kotlin.android") version "1.7.0" apply false
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
