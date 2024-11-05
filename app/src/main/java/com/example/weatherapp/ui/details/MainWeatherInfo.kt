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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Dimens.Small

@Composable
internal fun MainWeatherInfo(city: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_sunny_and_cloudy),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(Small))

        Text(
            text = "19°C",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(Small/2))

        Text(
            text = city,
            color = Color.White,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(Small/2))

        Text(
            text = "Max: 66.2°  Min: 44°",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}