package com.practicum.weatherapp.model

class WeatherDataResponse(
    val resolvedAddress: String,
    val address: String,
    val description: String,
    val days : List<DayWeather>,
    val currentConditions : CurrentWeather
    )
