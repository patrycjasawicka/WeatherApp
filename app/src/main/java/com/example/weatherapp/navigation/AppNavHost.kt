package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.weatherapp.ui.DetailsScreen
import com.example.weatherapp.ui.MainScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen(navController)
        }
        composable(
            NavigationItem.Details.route,
            arguments = listOf(navArgument(CITY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val city = backStackEntry.arguments?.getString(CITY) ?: ""
            DetailsScreen(city)
        }
    }
}