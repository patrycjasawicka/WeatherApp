package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.weatherapp.ui.DetailsScreen
import com.example.weatherapp.ui.MainScreen
import com.example.weatherapp.ui.WeatherViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Shared.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(startDestination = NavigationItem.Main.route, route = NavigationItem.Shared.route) {
            composable(NavigationItem.Main.route) {
                val viewModel: WeatherViewModel = hiltViewModel(navController.getBackStackEntry(NavigationItem.Shared.route))
                MainScreen(navController, viewModel)
            }
            composable(
                NavigationItem.Details.route,
                arguments = listOf(navArgument(CITY) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val city = backStackEntry.arguments?.getString(CITY) ?: ""
                val viewModel: WeatherViewModel = hiltViewModel(navController.getBackStackEntry(NavigationItem.Shared.route))
                DetailsScreen(city, viewModel)
            }
        }
    }
}