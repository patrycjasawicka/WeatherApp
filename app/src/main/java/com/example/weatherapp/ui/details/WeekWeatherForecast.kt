package com.example.weatherapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.DailyForecast
import com.example.weatherapp.ui.theme.Dimens.Medium

@Composable
internal fun WeekWeatherForecast(forecast: List<DailyForecast>) {
    Text(
        text = "5-Days Forecasts",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(Medium))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(forecast) { item ->
            ForecastDayItem(
                dayOrTime = item.date,
                temperature = "${item.temperatureMax}°C",
                iconId = item.dayIcon
            )
        }
    }
}