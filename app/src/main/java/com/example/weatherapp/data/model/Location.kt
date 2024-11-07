package com.example.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "Version")
    val version: Int,
    @Json(name = "Key")
    val key: String,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Rank")
    val rank: Int,
    @Json(name = "LocalizedName")
    val localizedName: String,
    @Json(name = "Country")
    val country: Country,
    @Json(name = "AdministrativeArea")
    val administrativeArea: AdministrativeArea
)
