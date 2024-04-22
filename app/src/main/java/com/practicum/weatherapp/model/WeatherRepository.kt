package com.practicum.weatherapp.model

import com.practicum.weatherapp.R

object WeatherRepository {
    val weatherForDays = listOf(
        WeatherForDay(
            day = "today",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 18,
            temperatureLow = 6
        ),
        WeatherForDay(
            day = "tue",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 14,
            temperatureLow = 3
        ),
        WeatherForDay(
            day = "wed",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 15,
            temperatureLow = 7
        ),
        WeatherForDay(
            day = "thu",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 17,
            temperatureLow = 11
        ),
        WeatherForDay(
            day = "fri",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 17,
            temperatureLow = 7
        ),
        WeatherForDay(
            day = "sat",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 17,
            temperatureLow = 8
        ),
        WeatherForDay(
            day = "sun",
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 14,
            temperatureLow = 1
        ),
    )
    val weatherForHours = listOf(
        WeatherForHour(
            time = 21,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 18,
        ),
        WeatherForHour(
            time = 22,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 15,
        ),
        WeatherForHour(
            time = 23,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 13,
        ),
        WeatherForHour(
            time = 0,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 11,
        ),
        WeatherForHour(
            time = 1,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 17,
        ),
        WeatherForHour(
            time = 2,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 18,
        ),
        WeatherForHour(
            time = 3,
            icon = R.drawable.ic_launcher_foreground,
            temperatureMain = 19,
        ),
    )
}