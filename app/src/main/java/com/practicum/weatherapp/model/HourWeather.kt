package com.practicum.weatherapp.model

import com.google.gson.annotations.SerializedName

data class HourWeather(
    @SerializedName("datetime") val date: String,
    @SerializedName("temp") val temp: Float,
    @SerializedName("icon") val icon: String,
)
