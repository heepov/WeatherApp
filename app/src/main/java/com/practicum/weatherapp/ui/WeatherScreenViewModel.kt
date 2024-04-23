package com.practicum.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.practicum.weatherapp.api.ApiInit
import com.practicum.weatherapp.model.WeatherUiSate
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
        ApiInit { weatherData ->
            if (weatherData != null) {
                _uiState.update { currentState->
                    currentState.copy(
                        location = weatherData.address,
                        date = weatherData.currentConditions.date,
                        tempNow = weatherData.currentConditions.temp.toInt(),
                        tempTodayMax = weatherData.days[0].tempMax.toInt(),
                        tempTodayMin = weatherData.days[0].tempMin.toInt(),
                        conditionsNow = weatherData.currentConditions.conditions
                    )
                }
            } else {
                // Обработка ошибки
            }
        }
    }

}