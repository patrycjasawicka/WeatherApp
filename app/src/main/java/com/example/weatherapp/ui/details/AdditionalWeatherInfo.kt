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
internal fun AdditionalWeatherInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoBox(
            label = "Air Quality",
            value = "5:23 AM",
            subText = "Sunset 7:23 PM",
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(Small))
        InfoBox(
            label = "UV Index",
            value = "4",
            subText = "Moderate",
            modifier = Modifier.weight(1f)
        )
    }
}