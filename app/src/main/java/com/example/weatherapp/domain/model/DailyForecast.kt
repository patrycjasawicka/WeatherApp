package com.example.weatherapp.domain.model

data class DailyForecast(
     val date: String,
     val temperatureMax: Double,
     val temperatureMin: Double,
     val dayIcon: Int,
     val nightIcon: Int,
)