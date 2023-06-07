/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

/*TODO: remove this if warning does not happen again in future updates */
@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "dev.marlonlom.apps.temocon"
  compileSdk = 33

  defaultConfig {
    applicationId = "dev.marlonlom.apps.temocon"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(libs.bundles.androidx)
  implementation(libs.bundles.compose)
  implementation(libs.bundles.material3)
  implementation(libs.timber)
  debugImplementation(libs.bundles.compose.debugOnly)
  testImplementation(libs.junit)
  androidTestImplementation(libs.bundles.androidTest)
}

apply(plugin = "io.gitlab.arturbosch.detekt")
