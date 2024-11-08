package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherClient
import com.example.weatherapp.data.model.CurrentConditions
import com.example.weatherapp.data.model.HourlyWeatherForecast
import com.example.weatherapp.data.model.Location
import com.example.weatherapp.data.model.WeekWeatherForecast
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

    suspend fun fetchHourlyForecasts(locationKey: String): Result<List<HourlyWeatherForecast>> = try {
        Result.success(WeatherClient.apiService.getHourlyForecast(locationKey))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun fetchDailyForecasts(locationKey: String): Result<WeekWeatherForecast> = try {
        Result.success(WeatherClient.apiService.getWeekForecast(locationKey))
    } catch (e: Exception) {
        Result.failure(e)
    }
}
