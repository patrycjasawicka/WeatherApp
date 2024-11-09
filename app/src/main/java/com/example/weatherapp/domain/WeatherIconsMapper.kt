package com.example.weatherapp.domain

import com.example.weatherapp.R
import javax.inject.Inject

class WeatherIconsMapper @Inject constructor() {
    fun getWeatherIcon(weatherCode: Int): Int =
        when (weatherCode) {
            1, 2, 5, 30, 31 -> R.drawable.ic_sunny
            3, 4 -> R.drawable.ic_sunny_and_cloudy
            6, 7, 8, 11 -> R.drawable.ic_cloudy
            12 -> R.drawable.ic_downpour_rainy
            13, 18, 26, 39, 40 -> R.drawable.ic_rainy
            14, 17 -> R.drawable.ic_rainy_sunny
            15, 16, 41, 42 -> R.drawable.ic_storm
            19, 20, 43, 44, 22, 23, 24, 25, 29 -> R.drawable.ic_snow
            21 -> R.drawable.ic_sun_snow
            32 -> R.drawable.ic_windy
            33, 34, 37 -> R.drawable.ic_moon
            35, 36, 38 -> R.drawable.ic_moon_clouds
            else -> R.drawable.ic_sunny
        }
}