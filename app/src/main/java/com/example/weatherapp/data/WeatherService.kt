package com.example.weatherapp.data


import com.example.weatherapp.data.model.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("locations/v1/cities/autocomplete")
    suspend fun getLocations(@Query("q") query: String): List<Location>
}