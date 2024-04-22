package com.practicum.weatherapp.model

import androidx.annotation.DrawableRes

data class WeatherForDay(
    val day: String,
    @DrawableRes val icon: Int,
    val temperatureMain: Int,
    val temperatureLow: Int,
)
