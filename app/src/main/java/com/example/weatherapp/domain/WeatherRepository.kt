package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherClient
import com.example.weatherapp.data.model.CurrentConditions
import com.example.weatherapp.data.model.Location
import javax.inject.Inject

class WeatherRepository @Inject constructor() {

    suspend fun fetchLocations(query: String): Result<List<Location>> = try {
        Result.success(WeatherClient.apiService.getLocations(query))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun fetchCurrentConditions(locationKey: String): Result<List<CurrentConditions>> = try {
        Result.success(WeatherClient.apiService.getCurrentCondtions(locationKey))
    } catch (e: Exception) {
        Result.failure(e)
    }
}
