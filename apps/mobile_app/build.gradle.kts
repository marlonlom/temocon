/*
 * Copyright 2023 Marlonlom
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 *
*/

plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
}

/*TODO: remove this if warning does not happen again in future updates */
@Suppress("UnstableApiUsage")
android {
  namespace = "dev.marlonlom.apps.talculator"
  compileSdk = 33

  defaultConfig {
    applicationId = "dev.marlonlom.apps.talculator"
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
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.3.3"
  }
  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
  implementation("androidx.activity:activity-compose:1.6.1")
  implementation("androidx.compose.ui:ui:1.3.3")
  implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
  implementation("androidx.compose.material3:material3:1.1.0-alpha05")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")
  debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")
}
