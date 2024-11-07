package com.example.weatherapp.domain.model

data class HourlyWeatherForecasts(
    val date: String,
    val hourlyForecasts: List<HourlyForecast>
)

data class HourlyForecast(
    val dateTime: String,
    val weatherIcon: Int,
    val temperature: Double,
)