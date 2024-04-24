package com.practicum.weatherapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practicum.weatherapp.model.UiDayForecast
import com.practicum.weatherapp.model.UiTimeForecast
import com.practicum.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CardListBig(
    weatherForecast: List<UiDayForecast>,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Card(
            modifier = modifier
                .clip(shapes.large)
        ) {
            LazyRow(
                modifier = modifier
                    .padding(vertical = 12.dp)
                    .background(colorScheme.surface)
            ) {
                items(weatherForecast.size) { index ->
                    ItemCardListBig(
                        weatherForecast[index],
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardListSmall(
    weatherForecast: List<UiTimeForecast>,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Card(
            modifier = modifier
                .clip(shapes.large)
                .background(colorScheme.surface)
        ) {
            LazyRow(
                modifier = modifier
                    .padding(vertical = 12.dp)
                    .background(colorScheme.surface)
            ) {
                items(weatherForecast.size) { index ->
                    ItemCardListSmall(
                        weatherForecast[index],
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCardListBig(
    weatherForecastDay: UiDayForecast,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            var color = colorScheme.primary
            if (weatherForecastDay.date.lowercase() != "today")
                color = colorScheme.tertiary
            Text(
                text = weatherForecastDay.date.uppercase(),
                style = typography.labelLarge,
                color = color
            )
            Icon(
                painter = painterResource(id = weatherForecastDay.icon),
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .size(32.dp)
                    .padding(vertical = 4.dp)
            )
            Text(
                text = "${weatherForecastDay.tempMax}°",
                style = typography.labelLarge,
                color = colorScheme.primary,

                )
            Text(
                text = "${weatherForecastDay.tempMin}°",
                style = typography.labelMedium,
                color = colorScheme.tertiary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun ItemCardListSmall(
    weatherForecastHour: UiTimeForecast,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            var color = colorScheme.primary
            if (weatherForecastHour.time.lowercase() != "now")
                color = colorScheme.tertiary
            Text(
                text = weatherForecastHour.time.uppercase(),
                style = typography.labelLarge,
                color = color
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = weatherForecastHour.icon),
                    contentDescription = null,
                    tint = colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "${weatherForecastHour.temp}°",
                    style = typography.labelLarge,
                    color = colorScheme.primary,
                )
            }
        }
    }
}
