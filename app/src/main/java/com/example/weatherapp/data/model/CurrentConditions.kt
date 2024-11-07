package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrentConditions(
    @Json(name = "LocalObservationDateTime") val localObservationDateTime: String,
    @Json(name = "EpochTime") val epochTime: Long,
    @Json(name = "WeatherText") val weatherText: String,
    @Json(name = "WeatherIcon") val weatherIcon: Int,
    @Json(name = "HasPrecipitation") val hasPrecipitation: Boolean,
    @Json(name = "PrecipitationType") val precipitationType: String?,
    @Json(name = "IsDayTime") val isDayTime: Boolean,
    @Json(name = "Temperature") val temperature: Temperature,
    @Json(name = "RelativeHumidity") val relativeHumidity: Int,
    @Json(name = "Wind") val wind: Wind,
    @Json(name = "UVIndex") val uvIndex: Int,
    @Json(name = "UVIndexText") val uvIndexText: String,
    @Json(name = "Pressure") val pressure: Pressure,
)

@JsonClass(generateAdapter = true)
data class Temperature(
    @Json(name = "Metric") val metric: TemperatureUnit,
)

@JsonClass(generateAdapter = true)
data class TemperatureUnit(
    @Json(name = "Value") val value: Double,
    @Json(name = "Unit") val unit: String,
    @Json(name = "UnitType") val unitType: Int
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "Direction") val direction: Direction,
    @Json(name = "Speed") val speed: WindSpeed
)

@JsonClass(generateAdapter = true)
data class Direction(
    @Json(name = "Degrees") val degrees: Int,
    @Json(name = "Localized") val localized: String,
    @Json(name = "English") val english: String
)

@JsonClass(generateAdapter = true)
data class WindSpeed(
    @Json(name = "Metric") val metric: Measurement,
    @Json(name = "Imperial") val imperial: Measurement
)

@JsonClass(generateAdapter = true)
data class Pressure(
    @Json(name = "Metric") val metric: Measurement,
    @Json(name = "Imperial") val imperial: Measurement
)

@JsonClass(generateAdapter = true)
data class Measurement(
    @Json(name = "Value") val value: Double,
    @Json(name = "Unit") val unit: String,
    @Json(name = "UnitType") val unitType: Int
)