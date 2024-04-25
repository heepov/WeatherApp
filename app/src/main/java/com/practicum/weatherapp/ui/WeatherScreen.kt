package com.practicum.weatherapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
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
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
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
    val density = LocalDensity.current
    val iconMinSize = 180.dp
    val iconMaxSize = 360.dp
    var iconSize by remember { mutableStateOf(iconMaxSize) }
    var appWallpaperAlpha by remember { mutableFloatStateOf(1f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                fun deltaDp() = with(density) { available.y.toDp() }
                if (available.y < 0 && iconSize > iconMinSize && state.firstVisibleItemIndex == 0) {
                    iconSize = max(iconMinSize, iconSize + deltaDp())
                }
                if (available.y > 0 && iconSize < iconMaxSize && state.firstVisibleItemIndex == 0) {
                    iconSize = min(iconMaxSize, iconSize + deltaDp())
                }
                appWallpaperAlpha = (iconSize - iconMinSize) / (iconMaxSize - iconMinSize)
                return Offset.Zero
            }
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
                        .padding(bottom = 32.dp)
                        .nestedScroll(nestedScrollConnection)
                ) {


                    item {
                        AppWallpaper(
                            icon = weatherUiState.wallpaperIcon,
                            description = weatherUiState.conditionsNow,
                            iconSize = iconSize,
                            iconAlpha = appWallpaperAlpha,
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(appWallpaperAlpha)
                        )
                    }
                    stickyHeader {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorScheme.background)
                                .padding(bottom = 16.dp)
                        ) {
                            CircleCardSimple(
                                value = weatherUiState.tempNow,
                                extraValueFirst = weatherUiState.tempTodayMax,
                                extraValueSecond = weatherUiState.tempTodayMin,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                            ) {
                                RectangleCardSmall(
                                    title = "${
                                        stringResource(id = R.string.precipitation_chance)
                                            .uppercase()
                                    } ${weatherUiState.precipProb}%",
                                    description = weatherUiState.presipProbLevel,
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                RectangleCardSmall(
                                    title = "${
                                        stringResource(id = R.string.pressure)
                                            .uppercase()
                                    } ${weatherUiState.pressureToday}",
                                    description = weatherUiState.pressureLevelToday,
                                    modifier = Modifier
                                )
                            }

                        }
                        Divider(
                            color = colorScheme.primary,
                            thickness = 2.dp,
                            modifier = Modifier
                                .alpha(1 - appWallpaperAlpha)
                            )
                    }

                    item {
                        Column(
                            modifier = Modifier
                        ) {
                            Spacer(modifier = Modifier.size(16.dp))
                            CardListSmall(weatherUiState.timeForecast)
                            Spacer(modifier = Modifier.size(16.dp))
                            CardListBig(weatherUiState.daysForecast)
                            Spacer(modifier = Modifier.size(16.dp))
                            Row() {
                                RectangleCardWithUnit(
                                    title = R.string.humidity,
                                    icon = R.drawable.humidity,
                                    value = weatherUiState.humiditToday,
                                    unit = R.string.percent,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                RectangleCardWithUnit(
                                    title = R.string.wind_speed,
                                    value = weatherUiState.windSpeedToday,
                                    direction = weatherUiState.windDirectionToday,
                                    unit = R.string.wind_unit_metric,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Row() {
                                RectangleCardWithDescription(
                                    title = R.string.uv_index,
                                    description = weatherUiState.uvIndexLevelToday,
                                    icon = weatherUiState.uvIcon,
                                    value = weatherUiState.uvIndexToday,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                RectangleCardSimple(
                                    title = R.string.sunrise,
                                    value = weatherUiState.sunriseToday,
                                    icon = R.drawable.sunrise,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Row() {
                                RectangleCardSimple(
                                    title = R.string.sunset,
                                    value = weatherUiState.sunsetToday,
                                    icon = R.drawable.sunset,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                CircleCardWithDescription(
                                    description = R.string.feels_like,
                                    value = weatherUiState.tempFeelsLike,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
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
            .background(colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                tint = colorScheme.primary,
                contentDescription = null,
            )
        }

        Text(
            text = place.capitalize(),
            style = typography.titleLarge,
            color = colorScheme.primary
        )
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                tint = colorScheme.primary,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun AppWallpaper(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconSize: Dp,
    iconAlpha: Float,
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
            tint = colorScheme.primary,
            modifier = Modifier
                .size(iconSize)
                .alpha(iconAlpha)
        )
        Text(
            text = description,
            style = typography.titleLarge,
            color = colorScheme.primary
        )
    }
}

