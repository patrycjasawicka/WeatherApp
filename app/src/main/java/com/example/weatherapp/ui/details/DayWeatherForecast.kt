package com.example.weatherapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Dimens.Medium
import com.example.weatherapp.ui.theme.Dimens.RoundedCornersSize

@Composable
internal fun DayWeatherForecast() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3E2286), shape = RoundedCornerShape(RoundedCornersSize))
            .padding(Medium)
    ) {
        DateRow()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ForecastDayItem(dayOrTime = "8:00", temperature = "20°C", iconId = R.drawable.ic_sunny)
            ForecastDayItem(dayOrTime = "12:00", temperature = "23°C", iconId = R.drawable.ic_sunny)
            ForecastDayItem(dayOrTime = "16:00", temperature = "32°C", iconId = R.drawable.ic_sunny)
            ForecastDayItem(dayOrTime = "20:00", temperature = "33°C", iconId = R.drawable.ic_sunny)
            ForecastDayItem(dayOrTime = "00:00", temperature = "16°C", iconId = R.drawable.ic_sunny)
        }
    }
}