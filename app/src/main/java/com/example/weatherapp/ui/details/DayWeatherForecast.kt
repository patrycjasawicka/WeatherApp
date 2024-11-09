package com.example.weatherapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.HourlyWeatherForecasts
import com.example.weatherapp.ui.theme.Colors.DarkIndigo
import com.example.weatherapp.ui.theme.Dimens.Medium
import com.example.weatherapp.ui.theme.Dimens.RoundedCornersSize

@Composable
internal fun DayWeatherForecast(dailyForecast: HourlyWeatherForecasts) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkIndigo, shape = RoundedCornerShape(RoundedCornersSize))
            .padding(Medium)
    ) {
        DateRow(dailyForecast.date)
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(dailyForecast.hourlyForecasts) {
                ForecastDayItem(
                    dayOrTime = it.dateTime,
                    temperature = it.temperature,
                    iconId = it.weatherIcon
                )
            }
        }
    }
}