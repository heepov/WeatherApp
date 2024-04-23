package com.practicum.weatherapp.api

import com.practicum.weatherapp.model.WeatherDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("VisualCrossingWebServices/rest/services/timeline/{location}")
    fun getWeatherForecast(
        @Path("location") location: String,
        @Query("unitGroup") unitGroup: String,
        @Query("include") include: String,
        @Query("key") key: String,
        @Query("contentType") contentType: String,
        ): Call<WeatherDataResponse>
}
// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/moscow?unitGroup=metric&include=events%2Ccurrent%2Cdays%2Chours%2Calerts&key=88G6BX8BBEEWRN3KL86KC9EX7&contentType=json