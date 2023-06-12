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
import dev.marlonlom.utilities.temocon.core.TemperatureConvertRequest
import dev.marlonlom.utilities.temocon.core.TemperatureConvertResponse
import dev.marlonlom.utilities.temocon.core.TemperatureConverter
import dev.marlonlom.utilities.temocon.core.TemperatureUnit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Home screen ui state data class.
 *
 * @author marlonlom
 */
data class HomeUiState(
  val isAppInDarkTheme: Boolean = false,
  val selectedTemperatureUnitIndex: Int = 0
)

/**
 * Home screen ui view model class.
 *
 * @author marlonlom
 */
class HomeViewModel(
  private val preferencesStore: PreferencesStore,
  private val temperatureConverter: TemperatureConverter = TemperatureConverter(),
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
  private val _homeState = MutableStateFlow(initial)
  private val _temperatureValueState = MutableStateFlow(Double.NaN)
  private val _responseState = MutableStateFlow(initialResponse)

  val homeState = _homeState.stateIn(viewModelScope, SharingStarted.Eagerly, initial)
  val responseState = _responseState.stateIn(viewModelScope, SharingStarted.Eagerly, initialResponse)

  init {
    viewModelScope.launch {
      collectAndSavePreferencesStoreState()
    }
  }

  private suspend fun collectAndSavePreferencesStoreState() {
    preferencesStore.getPreferencesStoreState().collect { state ->
      _homeState.update {
        it.copy(
          isAppInDarkTheme = state.isNightMode,
          selectedTemperatureUnitIndex = state.defaultTemperatureUnitIndex
        )
      }
    }
  }

  /**
   * Toggles IsAppInDarkTheme flag to true/false.
   *
   * @param isDarkTheme true/false if application is in dark theme.
   */
  fun toggleIsAppInDarkThemeFlag(isDarkTheme: Boolean) {
    viewModelScope.launch(context = coroutineDispatcher) {
      preferencesStore.toggleNightMode(isDarkTheme)
      _homeState.update {
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
    viewModelScope.launch(context = coroutineDispatcher) {
      preferencesStore.saveSelectedTemperatureUnitIndex(selectedIndex)
      _homeState.update {
        it.copy(
          selectedTemperatureUnitIndex = selectedIndex
        )
      }
      updateConversionResponseState()
    }
  }

  private fun updateConversionResponseState() {
    val response = executeTemperatureConversion(_temperatureValueState.value)
    _responseState.update {
      it.copy(
        celsiusValue = response.celsiusValue,
        fahrenheitValue = response.fahrenheitValue,
        kelvinValue = response.kelvinValue,
        rankineValue = response.rankineValue
      )
    }
  }

  /**
   * Return conversion for selected temperature value.
   *
   * @param temperatureValue temperature value
   */
  fun convertTemperatureValue(temperatureValue: Double) {
    viewModelScope.launch(context = coroutineDispatcher) {
      _temperatureValueState.update { temperatureValue }
      updateConversionResponseState()
    }
  }

  private fun executeTemperatureConversion(temperatureValue: Double): TemperatureConvertResponse {
    val request = TemperatureConvertRequest(
      temperatureUnit = TemperatureUnit.values()[_homeState.value.selectedTemperatureUnitIndex],
      valueToConvert = temperatureValue
    )
    val response = temperatureConverter.calculate(
      request = request
    )
    Timber.d("[HomeViewModel.executeTemperatureConversion] conversion{request=$request,response=$response}")
    return response
  }

  companion object {
    private val initial = HomeUiState()
    private val initialResponse = TemperatureConvertResponse(
      celsiusValue = Double.NaN,
      fahrenheitValue = Double.NaN,
      kelvinValue = Double.NaN,
      rankineValue = Double.NaN
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
