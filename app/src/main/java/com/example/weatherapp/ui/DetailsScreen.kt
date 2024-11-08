package com.example.weatherapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.details.AdditionalWeatherInfo
import com.example.weatherapp.ui.details.DayWeatherForecast
import com.example.weatherapp.ui.details.MainWeatherInfo
import com.example.weatherapp.ui.details.Pressure
import com.example.weatherapp.ui.details.WeekWeatherForecast
import com.example.weatherapp.ui.theme.Colors.Indigo
import com.example.weatherapp.ui.theme.Dimens.ExtraLarge
import com.example.weatherapp.ui.theme.Dimens.Large
import com.example.weatherapp.ui.theme.Dimens.Medium

@Composable
fun DetailsScreen(city: String, weatherViewModel: WeatherViewModel) {

    val currentConditions by weatherViewModel.currentConditions.collectAsState()
    val hourlyForecast by weatherViewModel.hourlyForecast.collectAsState()
    val weekForecast by weatherViewModel.dailyForecast.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Indigo)
            .padding(Medium)
            .verticalScroll(scrollState) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(ExtraLarge))

        currentConditions?.let { conditions ->
            MainWeatherInfo(city, conditions.temperature, conditions.weatherIcon)
        }

        Spacer(modifier = Modifier.height(ExtraLarge))

        hourlyForecast?.let { forecast ->
            DayWeatherForecast(forecast)
        }

        Spacer(modifier = Modifier.height(Medium))

        weekForecast?.let { forecast ->
            WeekWeatherForecast(forecast)
        }

        currentConditions?.let { conditions ->
            Spacer(modifier = Modifier.height(Large))

            Pressure(conditions.pressure)

            Spacer(modifier = Modifier.height(Medium))

            AdditionalWeatherInfo(conditions.uvIndex, conditions.uvIndexText, conditions.relativeHumidity)
        }
    }
}