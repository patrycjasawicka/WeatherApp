package com.example.weatherapp.domain

import com.example.weatherapp.data.model.Wind

data class CurrentConditions(
    val weatherText: String,
    val weatherIcon: Int,
    val hasPrecipitation: Boolean,
    val precipitationType: String?,
    val isDayTime: Boolean,
    val temperature: Double,
    val relativeHumidity: Int,
    val wind: Wind,
    val uvIndex: Int,
    val uvIndexText: String,
    val pressure: Double,
)