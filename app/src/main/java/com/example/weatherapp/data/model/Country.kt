package com.example.weatherapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val ID: String,
    val LocalizedName: String
)