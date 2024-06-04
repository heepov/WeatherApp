package com.practicum.weatherapp.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practicum.weatherapp.R
import com.practicum.weatherapp.Routes
import com.practicum.weatherapp.TopBar
import com.practicum.weatherapp.fillViewModel
import com.practicum.weatherapp.model.SavedLocation
import com.practicum.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherScreenViewModel = WeatherScreenViewModel(),
    navController: NavController
) {
    val weatherUiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateSavedLocationsList()
    }

    WeatherAppTheme {
        Scaffold(
            topBar = {
                TopBar(
                    title = "LOCATIONS",
                    leftIcon = R.drawable.icon_back,
                    rightIcon = R.drawable.icon_pluse,
                    leftIconOnClick = { navController.navigate(Routes.WeatherScreen.route) },
                    rightIconOnClick = { navController.navigate(Routes.AddLocationScreen.route) }
                )
            }
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 32.dp)
            ) {
                items(weatherUiState.savedLocations.size) { index ->
                    Log.d("IO", "index ${weatherUiState.savedLocations[index]}")
                    LocationItem(
                        locationList = weatherUiState.savedLocations[index],
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }

    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    locationList: SavedLocation,
) {
    Column {
        Column(
            modifier = modifier
                .padding(vertical = 16.dp)
        ) {
            if (locationList.myLocation){
                Text(
                    text = "My location  ${locationList.currentConditions?.temp}°",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }else {
                Text(
                    text = "${locationList.getCity()}  ${locationList.currentConditions?.temp}°",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Text(
                text = locationList.currentConditions?.conditions?.uppercase() ?: "N/A",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 0.2.dp)
    }
}