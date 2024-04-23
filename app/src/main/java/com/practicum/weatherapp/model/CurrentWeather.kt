package com.practicum.weatherapp.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("datetime") val date: String,
    @SerializedName("temp") val temp: Float,
    @SerializedName("feelslike") val tempFeelsLike: Float,
    @SerializedName("humidity") val humidity: Float, // влажность %
    @SerializedName("pressure") val pressure: Float, // давление mb
    @SerializedName("precipprob") val precipProb: Float, // вероятность выпадения осадков %
    @SerializedName("windspeed") val windSpeed: Float,
    @SerializedName("winddir") val windDirection: Float,
    @SerializedName("uvindex") val uvIndex: Int,
    @SerializedName("conditions") val conditions: String,
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("icon") val icon: String,
)
