package com.practicum.weatherapp.api

import android.util.Log
import com.practicum.weatherapp.model.WeatherDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun ApiInit(
    location: String = "moscow",
    unitGroup: String = "metric",
    include: String = "current,days,hours",
    key: String = "88G6BX8BBEEWRN3KL86KC9EX7",
    callback: (WeatherDataResponse?) -> Unit // Колбэк для передачи данных обратно вызывающей стороне
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://weather.visualcrossing.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi = retrofit.create(WeatherApi::class.java)

    val call = weatherApi.getWeatherForecast(
        location = location,
        unitGroup = unitGroup,
        include = include,
        key = key,
        contentType = "json"
    )

    call.enqueue(object : Callback<WeatherDataResponse> {
        override fun onResponse(call: Call<WeatherDataResponse>, response: Response<WeatherDataResponse>) {
            if (response.isSuccessful) {
                val weatherData = response.body()
                callback(weatherData)
                Log.d("MainActivity", "Success")
            } else {
                callback(null) // В случае ошибки передаем null
                Log.d("MainActivity", "ERROR 1")
            }
        }

        override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
            callback(null) // В случае ошибки передаем null
            Log.d("MainActivity", "ERROR")
        }
    })
}
