package com.practicum.weatherapp.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.practicum.weatherapp.R

data class WeatherUiSate(
    val permissionState: Boolean = false,


    val iconMinSize: Dp = 100.dp,
    val iconMaxSize: Dp = 240.dp,

    val location: String = "",
    @DrawableRes val wallpaperIcon: Int = R.drawable.ic_launcher_foreground,
    val conditionsNow: String = "",
    val tempNow: Int = 0,
    val tempTodayMax: Int = 0,
    val tempTodayMin: Int = 0,
    val tempFeelsLike: Int = 0,
    val timeForecast: List<UiTimeForecast> = ArrayList(),
    val daysForecast: List<UiDayForecast> = ArrayList(),

    val humiditToday: Int = 0, // влажность %
    @DrawableRes val humiditIcon: Int = R.drawable.ic_launcher_foreground,

    val pressureToday: Int = 0, // давление mb
    val pressureLevelToday: String = "", // давление mb
    @DrawableRes val pressureIcon: Int = R.drawable.ic_launcher_foreground,

    val precipProb: Int = 0, // вероятность выпадения осадков %
    val presipProbLevel: String = "",
    @DrawableRes val precipProbIcon: Int = R.drawable.ic_launcher_foreground,

    val windSpeedToday: Int = 0,
    val windDirectionToday: Float = 0f,
    @DrawableRes val windIcon: Int = R.drawable.ic_launcher_foreground,

    val uvIndexToday: Int = 0,
    val uvIndexLevelToday: String = "",
    val severeRiskToday: Int = 0,
    @DrawableRes val uvIcon: Int = R.drawable.ic_launcher_foreground,

    val sunriseToday: String = "",
    @DrawableRes val sunriseIcon: Int = R.drawable.ic_launcher_foreground,

    val sunsetToday: String = "",
    @DrawableRes val sunsetIcon: Int = R.drawable.ic_launcher_foreground,
    )

data class UiTimeForecast(
    val time: String,
    val temp: Int,
    @DrawableRes val icon: Int,
)

data class UiDayForecast(
    val date: String,
    val tempMax: Int,
    val tempMin: Int,
    @DrawableRes val icon: Int,
)