package com.example.weatherapp.navigation

enum class Screen {
    MAIN,
    DETAILS,
}

const val CITY = "city"

sealed class NavigationItem(val route: String) {
    data object Main : NavigationItem(Screen.MAIN.name)
    data object Details : NavigationItem("${Screen.DETAILS.name}/{$CITY}") {
        fun getRoute(city: String) = "${Screen.DETAILS.name}/$city"
    }
}