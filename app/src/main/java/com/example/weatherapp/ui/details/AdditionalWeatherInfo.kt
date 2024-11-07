package com.example.weatherapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.theme.Dimens.Small

@Composable
internal fun AdditionalWeatherInfo(uvIndex: Int, uvIndexText: String, relativeHumidity: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoBox(
            label = "Humidity",
            value = "${relativeHumidity}%",
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(Small))
        InfoBox(
            label = "UV Index",
            value = uvIndex.toString(),
            subText = uvIndexText,
            modifier = Modifier.weight(1f)
        )
    }
}