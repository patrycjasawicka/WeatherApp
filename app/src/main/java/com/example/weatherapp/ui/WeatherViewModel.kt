package com.example.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.Location
import com.example.weatherapp.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    suspend fun fetchLocations(query: String) {
        viewModelScope.launch {
            val result = weatherRepository.fetchLocations(query)
            if(result.isSuccess) {
                _items.value = result.getOrDefault(emptyList()).map(::formattedName)
            } else {
                Log.e("WeatherViewModel", "error getting locations")
            }
        }
    }

    private fun formattedName(it: Location) =
        "${it.localizedName} (${it.administrativeArea.LocalizedName}, ${it.country.LocalizedName}) "
}