package com.example.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.Location
import com.example.weatherapp.domain.CurrentConditions
import com.example.weatherapp.domain.Hint
import com.example.weatherapp.domain.WeatherIconsMapper
import com.example.weatherapp.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val iconsMapper: WeatherIconsMapper
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

    fun fetchLocations(query: String) {
        viewModelScope.launch {
            val result = weatherRepository.fetchLocations(query)
            if (result.isSuccess) {
                _items.value = result.getOrDefault(emptyList()).map(::formattedName)
            } else {
                Log.e("WeatherViewModel", "error getting locations")
            }
        }
    }

    fun fetchCurrentConditions() {
        viewModelScope.launch {
            _selectedLocation.value?.let { selectedLocation ->
                val result =
                    weatherRepository.fetchCurrentConditions(selectedLocation.locationKey)
                if (result.isSuccess) {
                    _currentConditons.value =
                        result.getOrDefault(null)?.firstOrNull()?.let(::toCurrentConditions)
                } else {
                    Log.e(
                        "WeatherViewModel",
                        "error fetching current conditions ${result.exceptionOrNull()}"
                    )
                }
            }
        }
    }

    fun setSelectedLocation(hint: Hint) {
        _selectedLocation.value = hint
    }

    private fun formattedName(it: Location) = Hint(
        locationKey = it.key,
        localizedName = "${it.localizedName} (${it.administrativeArea.LocalizedName}, ${it.country.LocalizedName}) "
    )

    private fun toCurrentConditions(it: com.example.weatherapp.data.model.CurrentConditions) =
        CurrentConditions(
            weatherText = it.weatherText,
            weatherIcon = iconsMapper.getWeatherIcon(it.weatherIcon),
            hasPrecipitation = it.hasPrecipitation,
            precipitationType = it.precipitationType,
            isDayTime = it.isDayTime,
            pressure = it.pressure.metric.value,
            relativeHumidity = it.relativeHumidity,
            temperature = it.temperature.metric.value,
            uvIndex = it.uvIndex,
            uvIndexText = it.uvIndexText,
            wind = it.wind
        )
}