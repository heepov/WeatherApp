package com.practicum.weatherapp.model

import com.google.gson.annotations.SerializedName

data class DayWeather(
    @SerializedName("datetime") val date: String,
    @SerializedName("tempmax") val tempMax: Float,
    @SerializedName("tempmin") val tempMin: Float,
    @SerializedName("feelslike") val tempFeelsLike: Float,
    @SerializedName("humidity") val humidity: Float, // влажность %
    @SerializedName("pressure") val pressure: Float, // давление mb
    @SerializedName("precipprob") val precipProb: Float, // вероятность выпадения осадков %
    @SerializedName("windspeed") val windSpeed: Float,
    @SerializedName("winddir") val windDirection: Float,
    @SerializedName("uvindex") val uvIndex: Int,
    @SerializedName("severerisk") val severeRisk: Int,
    @SerializedName("conditions") val conditions: String,
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("hours") val hours: List<HourWeather>,

//    @DrawableRes val icon: Int,
)
