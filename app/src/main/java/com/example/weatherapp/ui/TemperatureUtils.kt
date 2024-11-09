package com.example.weatherapp.ui

import androidx.compose.ui.graphics.Color
import com.example.weatherapp.ui.theme.Colors.LightBlue

object TemperatureUtils {
    fun getColorBasedOnTemperature(temperature: Double) =
        when {
            temperature < 10.0 -> {
                LightBlue
            }

            temperature >= 10.0 && temperature < 20.0 -> {
                Color.Black
            }

            temperature >= 20.0 -> {
                Color.Red
            }

            else -> {
                Color.White
            }
        }
}