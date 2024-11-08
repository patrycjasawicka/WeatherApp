package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeekWeatherForecast(
    @Json(name = "Headline") val headline: Headline,
    @Json(name = "DailyForecasts") val dailyForecasts: List<DailyForecast>
)

@JsonClass(generateAdapter = true)
data class Headline(
    @Json(name = "EffectiveDate") val effectiveDate: String,
    @Json(name = "EndDate") val endDate: String,
)

@JsonClass(generateAdapter = true)
data class DailyForecast(
    @Json(name = "Date") val date: String,
    @Json(name = "Temperature") val temperature: TemperatureV3,
    @Json(name = "Day") val day: Day,
    @Json(name = "Night") val night: Night,
)

@JsonClass(generateAdapter = true)
data class TemperatureV3(
    @Json(name = "Minimum") val minimum: TemperatureValue,
    @Json(name = "Maximum") val maximum: TemperatureValue
)

@JsonClass(generateAdapter = true)
data class TemperatureValue(
    @Json(name = "Value") val value: Double,
    @Json(name = "Unit") val unit: String,
    @Json(name = "UnitType") val unitType: Int
)

@JsonClass(generateAdapter = true)
data class Day(
    @Json(name = "Icon") val icon: Int,
    @Json(name = "IconPhrase") val iconPhrase: String,
    @Json(name = "HasPrecipitation") val hasPrecipitation: Boolean
)

@JsonClass(generateAdapter = true)
data class Night(
    @Json(name = "Icon") val icon: Int,
    @Json(name = "IconPhrase") val iconPhrase: String,
    @Json(name = "HasPrecipitation") val hasPrecipitation: Boolean
)
