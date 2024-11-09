package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdministrativeArea(
    @Json(name = "ID")
    val id: String,
    @Json(name = "LocalizedName")
    val localizedName: String
)