package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherClient
import com.example.weatherapp.data.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor() {

    suspend fun fetchLocations(query: String): Result<List<Location>> = try {
        Result.success(WeatherClient.apiService.getLocations(query))
    } catch (e: Exception) {
        Result.failure(e)
    }
}
