package com.example.weatherapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.details.AdditionalWeatherInfo
import com.example.weatherapp.ui.details.AirQuality
import com.example.weatherapp.ui.details.DayWeatherForecast
import com.example.weatherapp.ui.details.MainWeatherInfo
import com.example.weatherapp.ui.details.WeekWeatherForecast
import com.example.weatherapp.ui.theme.Colors.Indigo
import com.example.weatherapp.ui.theme.Dimens.ExtraLarge
import com.example.weatherapp.ui.theme.Dimens.Large
import com.example.weatherapp.ui.theme.Dimens.Medium

@Composable
fun DetailsScreen(city: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Indigo)
            .padding(Medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(ExtraLarge))

        MainWeatherInfo(city)

        Spacer(modifier = Modifier.height(ExtraLarge))

        DayWeatherForecast()

        Spacer(modifier = Modifier.height(Medium))

        WeekWeatherForecast()

        Spacer(modifier = Modifier.height(Large))

        AirQuality()

        Spacer(modifier = Modifier.height(Medium))

        AdditionalWeatherInfo()
    }
}