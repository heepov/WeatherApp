package com.practicum.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.practicum.weatherapp.ui.WeatherScreen
import com.practicum.weatherapp.ui.WeatherScreenViewModel
import com.practicum.weatherapp.ui.theme.WeatherAppTheme

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            WeatherAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//                    WeatherScreen()
//                }
//            }
//        }
//    }
//}
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val weatherViewModel by viewModels<WeatherScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

            LaunchedEffect(Unit) {
                locationPermissionState.launchPermissionRequest()
            }

            WeatherScreen(weatherViewModel = weatherViewModel)
//
//            Scaffold(
//                content = { contentPadding ->
//                    when {
//                        locationPermissionState.hasPermission -> {
//                            WeatherScreen(weatherViewModel = viewModel, modifier = Modifier.padding(contentPadding))
//                        }
//                        locationPermissionState.shouldShowRationale || !locationPermissionState.permissionRequested  -> {
//                            Text("Permission required to access location.")
//                        }
//                        else -> {
//                            LaunchedEffect(Unit) {
//                                locationPermissionState.launchPermissionRequest()
//                            }
//                        }
//                    }
//                }
//            )
        }
    }
}
