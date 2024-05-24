package com.practicum.weatherapp.ui

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.weatherapp.R
import com.practicum.weatherapp.model.SavedLocation
import com.practicum.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LocationsScreen(
    viewModel: WeatherScreenViewModel = WeatherScreenViewModel(),
    modifier: Modifier = Modifier,
) {
    val weatherUiState by viewModel.uiState.collectAsState()
    var locatonList:List<SavedLocation>? = null

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            locatonList = viewModel.getLocationsList()
        }
    }


    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    val layoutDirection = LocalLayoutDirection.current
                    AppBar(
                        place = "Locations".uppercase(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateStartPadding(layoutDirection),
                                end = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateEndPadding(layoutDirection),
                            ),
                        iconLeft = R.drawable.icon_back,
                        iconRight = R.drawable.icon_pluse,
                        iconRightOnClick = { TODO() },
                        iconLeftOnClick = { (LocalContext.current as? Activity)?.finish() },
                    )
                }
            ) { contentPadding ->
                if (locatonList != null) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(contentPadding)
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 32.dp)
                    ) {
                        items(locatonList!!.size) { index ->
                            LocationItem(
                                locationList = locatonList!![index],
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
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
    locationList: SavedLocation
) {
    Column {
        Column(
            modifier = modifier
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "${locationList.address}  ${locationList.currentConditions.temp}°",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = locationList.description.uppercase(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 0.2.dp)
    }
}