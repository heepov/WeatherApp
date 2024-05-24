package com.practicum.weatherapp.model

data class SavedLocation(
    val resolvedAddress: String,
    val address: String,
    val description: String,
    val currentConditions : CurrentWeather
)