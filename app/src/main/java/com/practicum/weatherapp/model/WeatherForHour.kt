package com.practicum.weatherapp.model

import androidx.annotation.DrawableRes

data class WeatherForHour(
    val time: Int,
    @DrawableRes val icon: Int,
    val temperatureMain: Int,
    )
