package com.practicum.weatherapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.weatherapp.R
import com.practicum.weatherapp.ui.theme.WeatherAppTheme


@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherScreenViewModel = viewModel()
) {
    val weatherUiState by weatherViewModel.uiState.collectAsState()
    val state = rememberLazyListState()

    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    val layoutDirection = LocalLayoutDirection.current
                    AppBar(
                        place = weatherUiState.location,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateStartPadding(layoutDirection),
                                end = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateEndPadding(layoutDirection),
                            )
                    )
                }
            ) { contentPadding ->
                LazyColumn(
                    state = state,
                    modifier = Modifier
                        .padding(contentPadding)
                        .padding(horizontal = 20.dp)
                ) {
                    item {
                        AppWallpaper(
                            icon = weatherUiState.wallpaperIcon,
                            description = weatherUiState.conditionsNow,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    stickyHeader {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorScheme.surface)
                                .padding(bottom = 16.dp)
                        ) {
                            CircleCard(
                                temperatureMain = weatherUiState.tempNow,
                                temperatureHigh = weatherUiState.tempTodayMax,
                                temperatureLow = weatherUiState.tempTodayMin,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                            ) {
                                RectangleCardSmall(
                                    description = "Feels Like",
                                    title = "Temperature",
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                RectangleCardSmall(
                                    description = "Feels Like",
                                    title = "Temperature",
                                    modifier = Modifier
                                )
                            }
                        }
                    }

                    item {
                        WeatherScreenTmp()
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    place: String = "Test",
) {
    Row(
        modifier = modifier
            .background(colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
            )
        }

        Text(
            text = place,
            style = typography.titleLarge,
            color = colorScheme.primary
        )
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
            )
        }
    }
}


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun WeatherScreenBody(modifier: Modifier = Modifier) {
//    val state = rememberLazyListState()
//    WeatherAppTheme {
//        Surface(
//            modifier = modifier
//                .fillMaxSize()
//        ) {
//            LazyColumn(
//                state = state,
//                modifier = Modifier
//                    .padding(horizontal = 20.dp)
//            ) {
//                item {
//                    AppWallpaper(
//                        icon = R.drawable.ic_launcher_foreground,
//                        description = "Feels Like",
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//                stickyHeader {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(colorScheme.surface)
//                            .padding(bottom = 16.dp)
//                    ) {
//                        CircleCard(
//                            description = "Feels Like",
//                            temperatureMain = 20,
//                            temperatureHigh = 32,
//                            temperatureLow = -2,
//                            modifier = Modifier
//                                .weight(1f)
//                        )
//                        Column(
//                            modifier = Modifier
//                                .weight(1f),
//                        ) {
//                            RectangleCardSmall(
//                                description = "Feels Like",
//                                title = "Temperature",
//                                modifier = Modifier
//                            )
//                            Spacer(modifier = Modifier.size(16.dp))
//                            RectangleCardSmall(
//                                description = "Feels Like",
//                                title = "Temperature",
//                                modifier = Modifier
//                            )
//                        }
//                    }
//                }
//
//                item {
//                    WeatherScreenTmp()
//                }
//            }
//        }
//    }
//}

@Composable
fun WeatherScreenTmp(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.size(16.dp))
//        CardListSmall()
        Spacer(modifier = Modifier.size(16.dp))
//        CardListBig()
        Spacer(modifier = Modifier.size(16.dp))
        OneRow(
            first = {
                RectangleCardBig(
                    title = "Humidity",
                    description = "Humidity",
                    icon = R.drawable.ic_launcher_foreground,
                    value = "70",
                    modifier = Modifier.weight(1f)
                )
            },
            second = {
                RectangleCardBig(
                    title = "Humidity",
                    description = "Humidity",
                    icon = R.drawable.ic_launcher_foreground,
                    value = "70",
                    modifier = Modifier.weight(1f)
                )
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun AppWallpaper(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    description: String,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(360.dp)
        )
        Text(
            text = description,
            style = typography.titleLarge,
            color = colorScheme.primary
        )
    }
}

