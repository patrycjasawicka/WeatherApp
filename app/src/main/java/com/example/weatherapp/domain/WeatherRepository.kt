package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherClient
import com.example.weatherapp.data.model.CurrentConditions
import com.example.weatherapp.data.model.HourlyWeatherForecast
import com.example.weatherapp.data.model.Location
import com.example.weatherapp.data.model.WeekWeatherForecast
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherClient: WeatherClient) {
    suspend fun fetchLocations(query: String): Result<List<Location>> = try {
        Result.success(weatherClient.apiService.getLocations(query))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun fetchCurrentConditions(locationKey: String): Result<List<CurrentConditions>> = try {
        Result.success(weatherClient.apiService.getCurrentCondtions(locationKey))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun fetchHourlyForecasts(locationKey: String): Result<List<HourlyWeatherForecast>> =
        try {
            Result.success(weatherClient.apiService.getHourlyForecast(locationKey))
        } catch (e: Exception) {
            Result.failure(e)
        }

    suspend fun fetchDailyForecasts(locationKey: String): Result<WeekWeatherForecast> = try {
        Result.success(weatherClient.apiService.getWeekForecast(locationKey))
    } catch (e: Exception) {
        Result.failure(e)
    }
}
