package com.practicum.weatherapp.model

import androidx.annotation.DrawableRes
import com.practicum.weatherapp.R

data class WeatherUiSate(
    val date: String = "",
    val location: String = "",
    @DrawableRes val wallpaperIcon: Int = R.drawable.ic_launcher_foreground,
    val conditionsNow: String = "",
    val tempNow: Int = 0,
    val tempTodayMax: Int = 0,
    val tempTodayMin: Int = 0,
    val tempFeelsLike: Int = 0,
    val timeForecast: List<UiTimeForecast> = ArrayList(),
    val daysForecast: List<UiDayForecast> = ArrayList(),

    val humiditToday: Float = 0f, // влажность %
    @DrawableRes val humiditIcon: Int = R.drawable.ic_launcher_foreground,

    val pressureToday: Float = 0f, // давление mb
    @DrawableRes val pressureIcon: Int = R.drawable.ic_launcher_foreground,

    val precipProb: Float = 0f, // вероятность выпадения осадков %
    @DrawableRes val precipProbIcon: Int = R.drawable.ic_launcher_foreground,

    val windSpeedToday: Float = 0f,
    val windDirectionToday: Float = 0f,
    @DrawableRes val windIcon: Int = R.drawable.ic_launcher_foreground,

    val uvIndexToday: Int = 0,
    val severeRiskToday: Int = 0,
    @DrawableRes val uvIcon: Int = R.drawable.ic_launcher_foreground,

    val sunriseToday: String = "",
    @DrawableRes val sunriseIcon: Int = R.drawable.ic_launcher_foreground,

    val sunsetToday: String = "",
    @DrawableRes val sunsetIcon: Int = R.drawable.ic_launcher_foreground,
    )

data class UiTimeForecast(
    val time: Int,
    val temp: Int,
    @DrawableRes val icon: Int,
)

data class UiDayForecast(
    val date: String,
    val tempMax: Int,
    val tempMin: Int,
    @DrawableRes val icon: Int,
)