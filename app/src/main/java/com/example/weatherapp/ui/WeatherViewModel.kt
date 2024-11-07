package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun fetchLocations() = weatherRepository.fetchLocations()
}