package com.example.weatherapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Colors.DarkIndigo
import com.example.weatherapp.ui.theme.Dimens.Medium
import com.example.weatherapp.ui.theme.Dimens.RoundedCornersSize
import com.example.weatherapp.ui.theme.Dimens.Small

@Composable
internal fun Pressure(pressure: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkIndigo, shape = RoundedCornerShape(RoundedCornersSize))
            .padding(Medium)
    ) {
        Text(
            text = stringResource(R.string.pressure),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(Small))
        Text(
            text = stringResource(R.string.pressure_value, pressure),
            color = Color.White,
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.height(Small))
    }
}