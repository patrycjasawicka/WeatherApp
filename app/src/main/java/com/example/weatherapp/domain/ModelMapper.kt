package com.example.weatherapp.domain

import com.example.weatherapp.data.model.Location
import com.example.weatherapp.data.model.WeekWeatherForecast
import com.example.weatherapp.domain.model.CurrentConditions
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.domain.model.Hint
import com.example.weatherapp.domain.model.HourlyForecast
import com.example.weatherapp.domain.model.HourlyWeatherForecasts
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

typealias DataHourlyWeatherForecast = com.example.weatherapp.data.model.HourlyWeatherForecast
typealias DataCurrentConditions = com.example.weatherapp.data.model.CurrentConditions

class ModelMapper @Inject constructor(private val iconsMapper: WeatherIconsMapper) {
    fun toWeatherForecast(dataForecast: List<DataHourlyWeatherForecast>) =
        HourlyWeatherForecasts(
            date = formatDate(dataForecast.first().dateTime),
            hourlyForecasts = dataForecast.map {
                HourlyForecast(
                    dateTime = formatTime(it.dateTime),
                    weatherIcon = iconsMapper.getWeatherIcon(it.weatherIcon),
                    temperature = it.temperature.value
                )
            })

    fun formattedName(location: Location) = Hint(
        locationKey = location.key,
        localizedName = "${location.localizedName} (${location.administrativeArea.LocalizedName}, ${location.country.LocalizedName}) "
    )

    fun toCurrentConditions(currentConditions: DataCurrentConditions) =
        CurrentConditions(
            weatherText = currentConditions.weatherText,
            weatherIcon = iconsMapper.getWeatherIcon(currentConditions.weatherIcon),
            hasPrecipitation = currentConditions.hasPrecipitation,
            precipitationType = currentConditions.precipitationType,
            isDayTime = currentConditions.isDayTime,
            pressure = currentConditions.pressure.metric.value,
            relativeHumidity = currentConditions.relativeHumidity,
            temperature = currentConditions.temperature.metric.value,
            uvIndex = currentConditions.uvIndex,
            uvIndexText = currentConditions.uvIndexText,
            wind = currentConditions.wind
        )

    fun toWeekForecast(weekWeatherForecast: WeekWeatherForecast): List<DailyForecast> =
        weekWeatherForecast.dailyForecasts.map {
            DailyForecast(
                date = formatDate(it.date),
                dayIcon = iconsMapper.getWeatherIcon(it.day.icon),
                nightIcon = iconsMapper.getWeatherIcon(it.night.icon),
                temperatureMax = it.temperature.maximum.value,
                temperatureMin = it.temperature.minimum.value
            )
        }

    private fun formatTime(dateTime: String): String {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val zonedDateTime = ZonedDateTime.parse(dateTime, formatter)
        return zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    private fun formatDate(dateTime: String): String {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val zonedDateTime = ZonedDateTime.parse(dateTime, formatter)
        return zonedDateTime.format(DateTimeFormatter.ofPattern("dd.MM"))
    }
}