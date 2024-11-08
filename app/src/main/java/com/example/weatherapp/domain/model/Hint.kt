package com.example.weatherapp.domain.model

data class Hint(
    val locationKey: String,
    val localizedName: String,
    val isHistorical: Boolean = false
)
