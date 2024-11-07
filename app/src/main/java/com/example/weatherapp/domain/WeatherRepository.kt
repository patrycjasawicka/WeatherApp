package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor() {

    fun fetchLocations(query: String = "warsaw") {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val locations = WeatherClient.apiService.getLocations(query)
            } catch (e: Exception) {
            }
        }
    }
}
