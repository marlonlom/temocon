/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.ui.preferences

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

data class PreferencesStoreState(
  val isNightMode: Boolean,
  val defaultTemperatureUnitIndex: Int
)

interface PreferencesStore {
  fun getPreferencesStoreState(): Flow<PreferencesStoreState>
  suspend fun toggleNightMode(isDarkTheme: Boolean = false)
  suspend fun saveSelectedTemperatureUnitIndex(selectedIndex: Int)
}

private val Context.dataStore by preferencesDataStore("app_preferences")

private object PreferencesStoreKeys {
  val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enabled")
  val DEFAULT_TEMPERATURE_UNIT_INDEX = intPreferencesKey("default_temperature_unit_index")
}

class PreferencesStoreImpl
constructor(
  private val context: Context
) : PreferencesStore {

  override fun getPreferencesStoreState(): Flow<PreferencesStoreState> =
    context.dataStore.data
      .catch { exception: Throwable -> handleEmptyPreferencesOrThrowException(exception) }
      .map { preferences: Preferences ->
        Timber.d("[PreferencesStoreImpl.getPreferencesStoreState] prefs=$preferences")
        PreferencesStoreState(
          isNightMode = preferences[PreferencesStoreKeys.NIGHT_MODE_KEY] ?: false,
          defaultTemperatureUnitIndex = preferences[PreferencesStoreKeys.DEFAULT_TEMPERATURE_UNIT_INDEX] ?: 0
        )
      }

  override suspend fun toggleNightMode(isDarkTheme: Boolean) {
    context.dataStore.edit { preferences: MutablePreferences ->
      preferences[PreferencesStoreKeys.NIGHT_MODE_KEY] = isDarkTheme
    }
  }

  override suspend fun saveSelectedTemperatureUnitIndex(selectedIndex: Int) {
    context.dataStore.edit {
      val key = PreferencesStoreKeys.DEFAULT_TEMPERATURE_UNIT_INDEX
      it[key] = selectedIndex
      Timber.d("[PreferencesStoreImpl.saveSelectedTemperatureUnitIndex] prefs=$it")
    }
  }

  private suspend fun FlowCollector<Preferences>.handleEmptyPreferencesOrThrowException(
    exception: Throwable
  ) {
    if (exception is IOException) {
      emit(emptyPreferences())
    } else {
      throw exception
    }
  }
}
