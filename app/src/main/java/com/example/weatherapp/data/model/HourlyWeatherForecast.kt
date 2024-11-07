package com.example.weatherapp.data.model

import com.squareup.moshi.Json

data class HourlyWeatherForecast(
    @Json(name = "DateTime") val dateTime: String,
    @Json(name = "EpochDateTime") val epochDateTime: Long,
    @Json(name = "WeatherIcon") val weatherIcon: Int,
    @Json(name = "Temperature") val temperature: TemperatureV2,
)

data class TemperatureV2(
    @Json(name = "Value") val value: Double,
    @Json(name = "Unit") val unit: String,
    @Json(name = "UnitType") val unitType: Int
)
