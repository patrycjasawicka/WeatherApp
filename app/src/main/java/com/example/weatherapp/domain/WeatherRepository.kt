package com.example.weatherapp.domain

import android.util.Log
import com.example.weatherapp.data.WeatherClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class WeatherRepository {

    fun fetchLocations(query: String = "warsaw") {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val locations = WeatherClient.apiService.getLocations(query)
                Log.e("TEST", locations.toString())
            } catch (e: Exception) {
                Log.e("FetchLocations", e.message ?: "")
            }
        }
    }
//}
