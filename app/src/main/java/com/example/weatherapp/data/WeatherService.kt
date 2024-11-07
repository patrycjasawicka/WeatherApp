package com.example.weatherapp.data


import com.example.weatherapp.data.model.CurrentConditions
import com.example.weatherapp.data.model.HourlyWeatherForecast
import com.example.weatherapp.data.model.Location
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("locations/v1/cities/autocomplete")
    suspend fun getLocations(@Query("q") query: String): List<Location>

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getCurrentCondtions(
        @Path("locationKey") locationKey: String,
        @Query("details") details: Boolean = true
    ): List<CurrentConditions>

    @GET("forecasts/v1/hourly/12hour/{locationKey}")
    suspend fun getHourlyForecast(
        @Path("locationKey") locationKey: String,
        @Query("metric") metricValues: Boolean = true
    ): List<HourlyWeatherForecast>
}