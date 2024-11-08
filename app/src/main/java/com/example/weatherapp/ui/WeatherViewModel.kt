package com.example.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.ModelMapper
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.domain.model.CurrentConditions
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.Hint
import com.example.weatherapp.domain.model.HourlyWeatherForecasts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val modelMapper: ModelMapper
) :
    ViewModel() {

    private val _items = MutableStateFlow<List<Hint>>(emptyList())
    val items: StateFlow<List<Hint>> = _items

    private val _selectedLocation = mutableStateOf<Hint?>(null)
    val locationIsSelected: State<Boolean> = derivedStateOf {
        _selectedLocation.value != null
    }

    private val _currentConditons = MutableStateFlow<CurrentConditions?>(null)
    val currentConditions: StateFlow<CurrentConditions?> = _currentConditons

    private val _hourlyForecast = MutableStateFlow<HourlyWeatherForecasts?>(null)
    val hourlyForecast: StateFlow<HourlyWeatherForecasts?> = _hourlyForecast

    private val _dailyForecast = MutableStateFlow<List<DailyForecast>>(emptyList())
    val dailyForecast: StateFlow<List<DailyForecast>?> = _dailyForecast

    fun fetchLocations(query: String) {
        viewModelScope.launch {
            val result = weatherRepository.fetchLocations(query)
            if (result.isSuccess) {
                _items.value = result.getOrDefault(emptyList()).map(modelMapper::formattedName)
            } else {
                Log.e("WeatherViewModel", "error getting locations")
            }
        }
    }

    fun setSelectedLocation(hint: Hint) {
        _selectedLocation.value = hint
    }

    fun fetchDetails() {
        fetchCurrentConditions()
        fetchHourlyForecasts()
        getWeekForecast()
    }

    private fun fetchCurrentConditions() {
        viewModelScope.launch {
            _selectedLocation.value?.let { selectedLocation ->
                val result =
                    weatherRepository.fetchCurrentConditions(selectedLocation.locationKey)
                if (result.isSuccess) {
                    _currentConditons.value =
                        result.getOrDefault(null)?.firstOrNull()?.let(modelMapper::toCurrentConditions)
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
                    _hourlyForecast.value =
                        result.getOrDefault(emptyList()).let(modelMapper::toWeatherForecast)
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
                    _dailyForecast.value =
                        result.getOrNull()?.let(modelMapper::toWeekForecast) ?: emptyList()
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