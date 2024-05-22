package com.practicum.weatherapp

import android.Manifest
import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.practicum.weatherapp.ui.WeatherScreen
import com.practicum.weatherapp.ui.WeatherScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.internal.wait
import java.util.Locale
import kotlin.coroutines.resume

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<WeatherScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppContent(viewModel)
        }
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun WeatherAppContent(viewModel: WeatherScreenViewModel) {
    val locationPermissionState =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(locationPermissionState) {
        permissionGranted = getPermissions(locationPermissionState, context)
        if (permissionGranted == true) {
            // Если разрешение получено, запускаем FillViewModel в сопрограмме
            launch(Dispatchers.IO) {
                fillViewModel(locationPermissionState, context, viewModel)
            }
        }
    }

    permissionGranted?.let { permission ->
        if (permission) {
            WeatherScreen(viewModel)
        } else {
            WeatherScreen(viewModel)
            Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show()
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
private suspend fun getPermissions(
    locationPermissionState: PermissionState,
    context: Context
): Boolean {
    return when {
        locationPermissionState.hasPermission -> {
            Toast.makeText(context, "Permission allowed", Toast.LENGTH_SHORT).show()
            true
        }

        locationPermissionState.shouldShowRationale || !locationPermissionState.permissionRequested -> {
            locationPermissionState.launchPermissionRequest()
            Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
            false
        }

        else -> {
            locationPermissionState.launchPermissionRequest()
            Toast.makeText(context, "dont allowed", Toast.LENGTH_SHORT).show()
            false
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
public suspend fun fillViewModel(
    locationPermissionState: PermissionState,
    context: Context,
    viewModel: WeatherScreenViewModel
) {
        if (locationPermissionState.hasPermission) {
            val location = getLastKnownLocation(context)
            if (location != null) {
                val geocoder = Geocoder(context, Locale.getDefault())
                val address = geocoder.getFromLocation(location.first, location.second, 1)
                viewModel.fetchWeather(location, address)
            }
        } else {
            viewModel.fetchWeather()
        }

}
