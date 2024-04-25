package com.practicum.weatherapp.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class HourWeather(
    @SerializedName("datetimeEpoch") val dateTime: LocalDateTime,
    @SerializedName("temp") val temp: Float,
    @SerializedName("icon") val icon: String,
)
