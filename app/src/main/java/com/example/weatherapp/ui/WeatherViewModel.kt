package com.example.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.HintHistoryRepository
import com.example.weatherapp.domain.ModelMapper
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.CurrentConditions
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.Hint
import com.example.weatherapp.domain.model.HourlyWeatherForecasts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val modelMapper: ModelMapper,
    private val hintHistoryRepository: HintHistoryRepository
) : ViewModel() {

    private val _hints = MutableStateFlow<List<Hint>>(emptyList())
    val hints: StateFlow<List<Hint>> = _hints

    private val _selectedLocation = mutableStateOf<Hint?>(null)
    val locationIsSelected: State<Boolean> = derivedStateOf {
        _selectedLocation.value != null
    }

    private val _currentConditions = MutableStateFlow<CurrentConditions?>(null)
    val currentConditions: StateFlow<CurrentConditions?> = _currentConditions

    private val _hourlyForecast = MutableStateFlow<HourlyWeatherForecasts?>(null)
    val hourlyForecast: StateFlow<HourlyWeatherForecasts?> = _hourlyForecast

    private val _dailyForecast = MutableStateFlow<List<DailyForecast>>(emptyList())
    val dailyForecast: StateFlow<List<DailyForecast>> = _dailyForecast

    fun fetchLocations(query: String) {
        viewModelScope.launch {
            val historicalHints = hintHistoryRepository.getHints()
                .first()
                .filterNot { it.localizedName == query }
            if (query.isNotEmpty()) {
                handleFetchingLocations(query, historicalHints)
            } else {
                _hints.update { historicalHints }
            }
        }
    }

    fun onHintSelected(hint: Hint) {
        setSelectedLocation(hint)
        saveHintInHistory(hint)
    }

    fun fetchDetails() {
        fetchCurrentConditions()
        fetchHourlyForecasts()
        getWeekForecast()
    }

    private suspend fun handleFetchingLocations(
        query: String,
        historicalHints: List<Hint>
    ) {
        val result = weatherRepository.fetchLocations(query)
        if (result.isSuccess) {
            val mappedLocations =
                result.getOrDefault(emptyList()).map(modelMapper::toHint)
            _hints.update { historicalHints + mappedLocations }
        } else {
            Log.e("WeatherViewModel", "error getting locations")
        }
    }

    private fun saveHintInHistory(hint: Hint) {
        viewModelScope.launch {
            val annotatedHint = hint.copy(isHistorical = true)
            hintHistoryRepository.saveHint(annotatedHint)
        }
    }

    private fun setSelectedLocation(hint: Hint) {
        _selectedLocation.value = hint
    }

    private fun fetchCurrentConditions() {
        viewModelScope.launch {
            _selectedLocation.value?.let { selectedLocation ->
                val result =
                    weatherRepository.fetchCurrentConditions(selectedLocation.locationKey)
                if (result.isSuccess) {
                    _currentConditions.update {
                        result.getOrDefault(null)?.firstOrNull()
                            ?.let(modelMapper::toCurrentConditions)
                    }
                } else {
                    Log.e(
                        "WeatherViewModel",
                        "error fetching current conditions ${result.exceptionOrNull()}"
                    )
                }
            }
        }
    }

    private fun fetchHourlyForecasts() {
        viewModelScope.launch {
            _selectedLocation.value?.let { selectedLocation ->
                val result =
                    weatherRepository.fetchHourlyForecasts(selectedLocation.locationKey)
                if (result.isSuccess) {
                    _hourlyForecast.update {
                        result.getOrDefault(emptyList()).let(modelMapper::toWeatherForecast)
                    }
                } else {
                    Log.e(
                        "WeatherViewModel",
                        "error fetching hourly forecasts"
                    )
                }
            }
        }
    }

    private fun getWeekForecast() {
        viewModelScope.launch {
            _selectedLocation.value?.let { selectedLocation ->
                val result =
                    weatherRepository.fetchDailyForecasts(selectedLocation.locationKey)
                if (result.isSuccess) {
                    _dailyForecast.update {
                        result.getOrNull()?.let(modelMapper::toWeekForecast) ?: emptyList()
                    }
                } else {
                    Log.e(
                        "WeatherViewModel",
                        "error fetching daily forecasts"
                    )
                }
            }
        }
    }
}