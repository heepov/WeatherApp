package com.practicum.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.practicum.weatherapp.R
import com.practicum.weatherapp.api.ApiInit
import com.practicum.weatherapp.model.DayWeather
import com.practicum.weatherapp.model.UiDayForecast
import com.practicum.weatherapp.model.UiTimeForecast
import com.practicum.weatherapp.model.WeatherUiSate
import com.practicum.weatherapp.ui.WeatherScreenViewModel.weatherTypesAndLeves.iconsUvIndex
import com.practicum.weatherapp.ui.WeatherScreenViewModel.weatherTypesAndLeves.iconsWeather
import com.practicum.weatherapp.ui.WeatherScreenViewModel.weatherTypesAndLeves.precipProbLevel
import com.practicum.weatherapp.ui.WeatherScreenViewModel.weatherTypesAndLeves.pressureLevel
import com.practicum.weatherapp.ui.WeatherScreenViewModel.weatherTypesAndLeves.uvIndexLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class WeatherScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiSate())
    val uiState: StateFlow<WeatherUiSate> = _uiState.asStateFlow()
    private val currentDateTime = LocalDateTime.now()

    init {
        updateForecast()
    }

    private fun updateForecast() {
        val inputTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val outputTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        ApiInit(location = "moscow") { weatherData ->
            if (weatherData != null) {
                _uiState.update { currentState ->
                    currentState.copy(
                        location = weatherData.address,
                        wallpaperIcon = getWeatherIcon(weatherData.currentConditions.icon),

                        conditionsNow = weatherData.currentConditions.conditions,
                        tempNow = weatherData.currentConditions.temp.toInt(),
                        tempTodayMax = weatherData.days.first().tempMax.toInt(),
                        tempTodayMin = weatherData.days.first().tempMin.toInt(),
                        tempFeelsLike = weatherData.currentConditions.tempFeelsLike.toInt(),
                        humiditToday = weatherData.currentConditions.humidity.toInt(),
                        humiditIcon = R.drawable.humidity,

                        pressureToday = weatherData.currentConditions.pressure.toInt(),
                        pressureLevelToday = getPressureLevel(weatherData.currentConditions.pressure.toInt()),

                        precipProb = weatherData.currentConditions.precipProb.toInt(),
                        presipProbLevel = getPrecipProbLevel(weatherData.currentConditions.precipProb.toInt()),

                        windSpeedToday = weatherData.currentConditions.windSpeed.toInt(),
                        windDirectionToday = weatherData.currentConditions.windDirection,

                        uvIndexToday = weatherData.currentConditions.uvIndex,
                        uvIndexLevelToday = getUvIndexLevel(weatherData.currentConditions.uvIndex),
                        severeRiskToday = weatherData.days.first().severeRisk,
                        uvIcon = getUvIndexIcon(getUvIndexLevel(weatherData.currentConditions.uvIndex)),

                        sunriseToday = outputTimeFormat.format(inputTimeFormat.parse(weatherData.currentConditions.sunrise)),
                        sunriseIcon = R.drawable.sunrise,

                        sunsetToday = outputTimeFormat.format(inputTimeFormat.parse(weatherData.currentConditions.sunset)),
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
                list.add(
                    UiDayForecast(
                        date = getDayOfWeek(day.dateTime),
                        tempMax = day.tempMax.toInt(),
                        tempMin = day.tempMin.toInt(),
                        icon = getWeatherIcon(day.icon)
                    )
                )
        }
        return list.take(7)
    }

    private fun getDayOfWeek(dateTime: LocalDateTime): String {
        return if (currentDateTime.toLocalDate() == dateTime.toLocalDate()) {
            "Today"
        } else {
            dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).substring(0, 3)
        }
    }

    private fun getTimeForecast(listDays: List<DayWeather>): List<UiTimeForecast> {
        val list = mutableListOf<UiTimeForecast>()

        listDays.forEach { day ->
            day.hours.forEach { hour ->
                if (currentDateTime <= hour.dateTime.plusHours(1)) {
                    list.add(
                        UiTimeForecast(
                            time = getTimes(hour.dateTime),
                            temp = hour.temp.toInt(),
                            icon = getWeatherIcon(hour.icon)
                        )
                    )
                }
            }
        }
        return list.take(12)
    }

    private fun getTimes(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("HH")
        return if (dateTime.toLocalTime().hour == currentDateTime.toLocalTime().hour)
            "Now"
        else dateTime.format(formatter)
    }


    private fun getWeatherIcon(iconKey: String): Int {
        return if (iconsWeather.containsKey(iconKey) && iconsWeather[iconKey] != null) {
            iconsWeather[iconKey]!!
        } else
            R.drawable.sunny
    }

    private fun getUvIndexIcon(iconKey: String): Int {
        return if (iconsUvIndex.containsKey(iconKey) && iconsUvIndex[iconKey] != null) {
            iconsUvIndex[iconKey]!!
        } else
            R.drawable.uv_index_01
    }

    private fun getUvIndexLevel(uvIndex: Int): String {
        return when (uvIndex) {
            in 0..2 -> uvIndexLevel[0]
            in 3..5 -> uvIndexLevel[1]
            in 6..7 -> uvIndexLevel[2]
            in 8..10 -> uvIndexLevel[3]
            else -> uvIndexLevel[4]
        }
    }

    private fun getPressureLevel(pressure: Int): String {
        return when {
            pressure < 1000 -> pressureLevel[0]
            pressure < 1015 -> pressureLevel[1]
            else -> pressureLevel[2]
        }
    }

    private fun getPrecipProbLevel(precipProb: Int): String {
        return when {
            precipProb < 50 -> precipProbLevel[0]
            else -> precipProbLevel[1]
        }
    }

    fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

    private object weatherTypesAndLeves {
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

        val iconsUvIndex = mapOf(
            "Low" to R.drawable.uv_index_01,
            "Normal" to R.drawable.uv_index_02,
            "High" to R.drawable.uv_index_03,
            "Very High" to R.drawable.uv_index_04,
            "Extreme" to R.drawable.uv_index_05,
        )

        val pressureLevel = listOf(
            "Low",
            "Normal",
            "High"
        )
        val precipProbLevel = listOf(
            "Low",
            "High"
        )
        val uvIndexLevel = listOf(
            "Low",
            "Normal",
            "High",
            "Very High",
            "Extreme"
        )
    }
}
