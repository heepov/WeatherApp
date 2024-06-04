package com.practicum.weatherapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practicum.weatherapp.ui.AddLocationScreen
import com.practicum.weatherapp.ui.LocationsScreen
import com.practicum.weatherapp.ui.WeatherScreen
import com.practicum.weatherapp.ui.WeatherScreenViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: WeatherScreenViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.WeatherScreen.route
    )
    {
        composable(route = Routes.WeatherScreen.route) {
            WeatherScreen(navController = navController, viewModel  = viewModel)
        }
        composable(route = Routes.LocationsScreen.route) {
            LocationsScreen(navController = navController, viewModel  = viewModel)
        }
        composable(route = Routes.AddLocationScreen.route) {
            AddLocationScreen(navController = navController, viewModel  = viewModel)
        }
    }
}

sealed class Routes(val route: String) {
    object WeatherScreen : Routes("weather_screen")
    object LocationsScreen : Routes("locations_screen")
    object AddLocationScreen  : Routes("add_location_screen")
}