package com.example.weatherapp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Dimens.ForecastDayItemWidth
import com.example.weatherapp.ui.theme.Dimens.IconSize
import com.example.weatherapp.ui.theme.Dimens.Small


@Composable
internal fun ForecastDayItem(dayOrTime: String, temperature: String, iconId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(ForecastDayItemWidth)
    ) {
        Text(
            text = temperature,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(Small))
        Image(
            painter = painterResource(id = iconId),
            contentDescription = stringResource(id = R.string.weather_icon),
            modifier = Modifier.size(IconSize),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(Small))
        Text(
            text = dayOrTime,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}