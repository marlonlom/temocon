/*
* Copyright 2023 Marlonlom
*
* Use of this source code is governed by an MIT-style
* license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
*/

package dev.marlonlom.apps.temocon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.temocon.ui.preferences.PreferencesStore
import dev.marlonlom.utilities.temocon.core.TemperatureConvertResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Home screen ui state data class.
 *
 * @author marlonlom
 */
data class HomeUiState(
  val isAppInDarkTheme: Boolean = false,
  val selectedTemperatureUnitIndex: Int = 0,
  val temperatureValue: Double = Double.NaN,
  var temperatureResponse: TemperatureConvertResponse?
)

/**
 * Home screen ui view model class.
 *
 * @author marlonlom
 */
class HomeViewModel(
  private val preferencesStore: PreferencesStore
) : ViewModel() {
  private val _uiState = MutableStateFlow(initial)

  val uiState = _uiState
    .stateIn(
      viewModelScope,
      SharingStarted.Eagerly,
      initial
    )

  init {
    viewModelScope.launch {
      preferencesStore.getPreferencesStoreState().collect { state ->
        _uiState.update {
          it.copy(
            isAppInDarkTheme = state.isNightMode,
            selectedTemperatureUnitIndex = state.defaultTemperatureUnitIndex
          )
        }
      }
    }
  }

  /**
   * Toggles IsAppInDarkTheme flag to true/false.
   *
   * @param isDarkTheme true/false if application is in dark theme.
   */
  fun toggleIsAppInDarkThemeFlag(isDarkTheme: Boolean) {
    viewModelScope.launch {
      preferencesStore.toggleNightMode(isDarkTheme)
      _uiState.update {
        it.copy(
          isAppInDarkTheme = isDarkTheme
        )
      }
    }
  }

  /**
   * Updates selected temperature unit index from button click in the ui.
   *
   * @param selectedIndex selected temperature unit index from the ui.
   */
  fun updateSelectedTemperatureUnitIndex(selectedIndex: Int) {
    viewModelScope.launch {
      preferencesStore.saveSelectedTemperatureUnitIndex(selectedIndex)
      _uiState.update {
        it.copy(
          selectedTemperatureUnitIndex = selectedIndex
        )
      }
    }
  }

  companion object {
    private val initial = HomeUiState(
      temperatureResponse = null
    )

    /**
     * Provides home view model factory class instance.
     *
     * @param preferencesStore application preferences store instance.
     * @return ViewModelProvider.Factory
     */
    fun provideFactory(
      preferencesStore: PreferencesStore
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(preferencesStore) as T
      }
    }
  }
}
