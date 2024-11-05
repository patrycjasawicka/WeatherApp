package com.example.weatherapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Dimens.Medium

@Composable
internal fun WeekWeatherForecast() {
    Text(
        text = "7-Days Forecasts",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(Medium))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ForecastDayItem(dayOrTime = "Mon", temperature = "20°C", iconId = R.drawable.ic_sunny)
        ForecastDayItem(dayOrTime = "Tue", temperature = "20°C", iconId = R.drawable.ic_sunny)
        ForecastDayItem(dayOrTime = "Wed", temperature = "20°C", iconId = R.drawable.ic_sunny)
        ForecastDayItem(dayOrTime = "Thu", temperature = "20°C", iconId = R.drawable.ic_sunny)
    }
}