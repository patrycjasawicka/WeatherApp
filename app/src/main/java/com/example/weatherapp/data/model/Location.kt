package com.example.weatherapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val Version: Int,
    val Key: String,
    val Type: String,
    val Rank: Int,
    val LocalizedName: String,
    val Country: Country,
    val AdministrativeArea: AdministrativeArea
)
