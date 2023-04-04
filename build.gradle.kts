/*
 * Copyright 2023 Marlonlom
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 *
 */

/*TODO: remove this if warning does not happen again in future updates */
@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.spotless) apply false
  alias(libs.plugins.detekt) apply false
}

apply(plugin = "io.gitlab.arturbosch.detekt")

/* Applying additional configs */
subprojects {
  repositories {
    google()
    mavenCentral()
  }

  val ktlintEditorConfigOverridesMap = mapOf(
    "android" to true,
    "indent_size" to 2,
    "ij_continuation_indent_size" to 2,
    "trim_trailing_whitespace" to true,
    "insert_final_newline" to true,
    "end_of_line" to "lf",
    "max_line_length" to 120,
  )

  apply(plugin = "com.diffplug.spotless")
  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
      target("**/src/*.kt")
      targetExclude("$buildDir/**/*.kt")
      ktlint().editorConfigOverride(ktlintEditorConfigOverridesMap)
      trimTrailingWhitespace()
      indentWithSpaces(2)
      endWithNewline()
      licenseHeaderFile(File("${project.rootDir}/spotless/copyright.kt"))
    }
    kotlinGradle {
      target("**/*.gradle.kts", "*.gradle.kts")
      targetExclude("$buildDir/**/*.kts")
      ktlint().editorConfigOverride(ktlintEditorConfigOverridesMap)
      trimTrailingWhitespace()
      indentWithSpaces(2)
      endWithNewline()
    }
  }

  apply(plugin = "io.gitlab.arturbosch.detekt")
}
