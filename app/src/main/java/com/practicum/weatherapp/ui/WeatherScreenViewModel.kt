package com.practicum.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.practicum.weatherapp.R
import com.practicum.weatherapp.api.ApiInit
import com.practicum.weatherapp.model.DayWeather
import com.practicum.weatherapp.model.HourWeather
import com.practicum.weatherapp.model.UiDayForecast
import com.practicum.weatherapp.model.UiTimeForecast
import com.practicum.weatherapp.model.WeatherUiSate
import com.practicum.weatherapp.ui.WeatherScreenViewModel.weatherCondidtions.iconsWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiSate())
    val uiState: StateFlow<WeatherUiSate> = _uiState.asStateFlow()

    init {
        updateForecast()
    }

    private fun updateForecast() {
        ApiInit(location = "cairo") { weatherData ->
            if (weatherData != null) {
                _uiState.update { currentState ->
                    currentState.copy(
                        date = weatherData.currentConditions.date,
                        location = weatherData.address,
                        wallpaperIcon = getIcon(weatherData.currentConditions.icon),

                        conditionsNow = weatherData.currentConditions.conditions,
                        tempNow = weatherData.currentConditions.temp.toInt(),
                        tempTodayMax = weatherData.days.first().tempMax.toInt(),
                        tempTodayMin = weatherData.days.first().tempMin.toInt(),
                        tempFeelsLike = weatherData.currentConditions.tempFeelsLike.toInt(),
                        humiditToday = weatherData.currentConditions.humidity.toInt(),
                        humiditIcon = R.drawable.humidity,

                        pressureToday = weatherData.currentConditions.pressure.toInt(),
//                        pressureIcon = R.drawable.humidity,

                        precipProb = weatherData.currentConditions.precipProb.toInt(),
//                        precipProbIcon = R.drawable.humidity,

                        windSpeedToday = weatherData.currentConditions.windSpeed.toInt(),
                        windDirectionToday = weatherData.currentConditions.windDirection,
//                        windIcon =,

                        uvIndexToday = weatherData.days.first().uvIndex,
                        severeRiskToday = weatherData.days.first().severeRisk,
                        uvIcon = R.drawable.uv_index,

                        sunriseToday = weatherData.currentConditions.sunrise,
                        sunriseIcon = R.drawable.sunrise,

                        sunsetToday = weatherData.currentConditions.sunset,
                        sunsetIcon = R.drawable.sunset,

                        timeForecast = getTimeForecast(weatherData.days),
                        daysForecast = getDaysForecast(weatherData.days),
                    )
                }
            } else {
                // Обработка ошибки
            }
        }
    }
    private fun getDaysForecast(listDays: List<DayWeather>): List<UiDayForecast> {
        val list = mutableListOf<UiDayForecast>()
        listDays.forEach { day ->
            list.add(UiDayForecast(
                date = day.date,
                tempMax = day.tempMax.toInt(),
                tempMin = day.tempMin.toInt(),
                icon = getIcon(day.icon)
            ))
        }
        return list
    }
    private fun getTimeForecast(listDays: List<DayWeather>): List<UiTimeForecast> {
        val list = mutableListOf<UiTimeForecast>()
        listDays.forEach { day ->
            day.hours.forEach {hour ->
                list.add(UiTimeForecast(
                    time = hour.date,
                    temp = hour.temp.toInt(),
                    icon = getIcon(hour.icon)
                ))
            }
        }
        return list
    }

    private fun getIcon(iconKey: String): Int {
        return if (iconsWeather.containsKey(iconKey) && iconsWeather[iconKey] != null) {
            iconsWeather[iconKey]!!
        } else
            R.drawable.sunny
    }

    object weatherCondidtions {
        val iconsWeather = mapOf(
            "snow" to R.drawable.rainy,
            "rain" to R.drawable.rainy,
            "fog" to R.drawable.mostly_cloudy,
            "wind" to R.drawable.cloudy,
            "cloudy" to R.drawable.cloudy,
            "partly-cloudy-day" to R.drawable.partly_sunny,
            "partly-cloudy-night" to R.drawable.partly_sunny,
            "clear-day" to R.drawable.sunny,
            "clear-night" to R.drawable.sunny
        )
    }
}
