package com.example.weatherapp.domain

import com.example.weatherapp.data.model.Location
import com.example.weatherapp.domain.model.CurrentConditions
import com.example.weatherapp.domain.model.Hint
import com.example.weatherapp.domain.model.HourlyForecast
import com.example.weatherapp.domain.model.HourlyWeatherForecasts
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ModelMapper @Inject constructor(private val iconsMapper: WeatherIconsMapper) {
    fun toWeatherForecast(it: List<com.example.weatherapp.data.model.HourlyWeatherForecast>) =
        HourlyWeatherForecasts(
            date = formatDate(it.first().dateTime),
            hourlyForecasts = it.map {
                HourlyForecast(
                    dateTime = formatTime(it.dateTime),
                    weatherIcon = iconsMapper.getWeatherIcon(it.weatherIcon),
                    temperature = it.temperature.value
                )
            })

    fun formattedName(it: Location) = Hint(
        locationKey = it.key,
        localizedName = "${it.localizedName} (${it.administrativeArea.LocalizedName}, ${it.country.LocalizedName}) "
    )

    fun toCurrentConditions(it: com.example.weatherapp.data.model.CurrentConditions) =
        CurrentConditions(
            weatherText = it.weatherText,
            weatherIcon = iconsMapper.getWeatherIcon(it.weatherIcon),
            hasPrecipitation = it.hasPrecipitation,
            precipitationType = it.precipitationType,
            isDayTime = it.isDayTime,
            pressure = it.pressure.metric.value,
            relativeHumidity = it.relativeHumidity,
            temperature = it.temperature.metric.value,
            uvIndex = it.uvIndex,
            uvIndexText = it.uvIndexText,
            wind = it.wind
        )

    private fun formatTime(dateTime: String): String {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val zonedDateTime = ZonedDateTime.parse(dateTime, formatter)
        return zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun formatDate(dateTime: String): String {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val zonedDateTime = ZonedDateTime.parse(dateTime, formatter)
        return zonedDateTime.format(DateTimeFormatter.ofPattern("MMMM, dd"))
    }
}