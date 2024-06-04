package com.practicum.weatherapp.model

import android.util.Log

data class SavedLocation(
    val resolvedAddress: String,
    val address: String,
    val currentConditions : CurrentWeather?,
    val myLocation: Boolean,
) {
    fun getCity():String{
        if(resolvedAddress != ""){
            return resolvedAddress.split(",", limit = 2)[0].trim()
            }
        else
            return "Not found"
    }
    fun getCountry():String{
        if(resolvedAddress != "") {
            return if(resolvedAddress.split(",", limit = 2).size == 1)
                ""
            else resolvedAddress.split(",", limit = 2)[1].trim()
        }
        else
            return "please try again"
    }
}