package com.practicum.weatherapp

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.practicum.weatherapp.ui.WeatherScreen
import com.practicum.weatherapp.ui.WeatherScreenViewModel
import com.practicum.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<WeatherScreenViewModel>()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val locationPermissionsState = rememberMultiplePermissionsState(
                listOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                )
            )
            val locationPermissions = locationPermissionsState.allPermissionsGranted
            if (locationPermissions) {
                viewModel.changePermissionState(locationPermissions)
                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        fillViewModel(context, viewModel)
                    }
                }
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            } else {
                val allPermissionsRevoked =
                    locationPermissionsState.permissions.size ==
                            locationPermissionsState.revokedPermissions.size

                val textToShow = if (!allPermissionsRevoked) {
                    viewModel.changePermissionState(locationPermissions)
                    // If not all the permissions are revoked, it's because the user accepted the COARSE
                    // location permission, but not the FINE one.
                    "Yay! Thanks for letting me access your approximate location. " +
                            "But you know what would be great? If you allow me to know where you " +
                            "exactly are. Thank you!"
                } else if (locationPermissionsState.shouldShowRationale) {
                    viewModel.changePermissionState(locationPermissions)
                    // Both location permissions have been denied
                    "Getting your exact location is important for this app. " +
                            "Please grant us fine location. Thank you :D"
                } else {
                    viewModel.changePermissionState(locationPermissions)
                    // First time the user sees this feature or the user doesn't want to be asked again
                    "This application requires location permission. Please grant us fine location. Thank you :D"
                }

                val buttonText = if (!allPermissionsRevoked) {
                    "Allow precise location"
                } else {
                    "Request permissions"
                }
                PermissionsScreen(
                    textToShow = textToShow,
                    buttonText = buttonText,
                    onClick = { locationPermissionsState.launchMultiplePermissionRequest() }
                )

            }
        }
    }
}

public suspend fun fillViewModel(
    context: Context,
    viewModel: WeatherScreenViewModel
) {
    val location = getLastKnownLocation(context)
    if (location != null) {
        val geocoder = Geocoder(context, Locale.getDefault())
        val address = geocoder.getFromLocation(location.first, location.second, 1)
        viewModel.fetchAllWeather(location, address)
    }
}
@Preview
@Composable
fun PermissionsScreen(
    textToShow: String="\"Getting your exact location is important for this app. Please grant us fine location. Thank you :D\"",
    buttonText: String="Request permissions",
    onClick: () -> Unit = {},
) {
    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Spacer(
//                    modifier = Modifier
//                        .weight(1f)
//                )
                Icon(
                    painter = painterResource(id = R.drawable.icon_location),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(160.dp)
                        .weight(1f)
                )
                Text(
                    modifier = Modifier
                        .weight(0.3f),
                    text = textToShow.uppercase(),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(1f / 0.45f)
                        .padding(16.dp)
                        .padding(horizontal = 8.dp),
                    onClick = { onClick() }) {
                    Text(
                        text = buttonText.uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

        }
    }
}
