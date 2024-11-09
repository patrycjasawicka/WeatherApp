package com.example.weatherapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Temperature
import com.example.weatherapp.ui.TemperatureUtils
import com.example.weatherapp.ui.theme.Dimens.IconSizeBig
import com.example.weatherapp.ui.theme.Dimens.Small

@Composable
internal fun MainWeatherInfo(city: String, temperature: Double, weatherIcon: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = weatherIcon),
            contentDescription = stringResource(id = R.string.weather_icon),
            modifier = Modifier.size(IconSizeBig),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(Small))

        Text(
            text = stringResource(id = R.string.temperature_value, temperature),
            color = TemperatureUtils.getColorBasedOnTemperature(temperature),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(Small/2))

        Text(
            text = city,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}