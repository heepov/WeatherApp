package com.practicum.weatherapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.weatherapp.model.WeatherForDay
import com.practicum.weatherapp.model.WeatherForHour
import com.practicum.weatherapp.model.WeatherRepository.weatherForDays
import com.practicum.weatherapp.model.WeatherRepository.weatherForHours
import com.practicum.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CardListBig(
    weatherForecast: List<WeatherForDay>,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Card(
            modifier = modifier
                .clip(MaterialTheme.shapes.large)
        ) {
            LazyRow(
                modifier = modifier
                    .padding(vertical = 12.dp)
            ) {
                items(weatherForecast.size) { index ->
                    ItemCardListBig(
                        weatherForecast[index],
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardListSmall(
    weatherForecast: List<WeatherForHour>,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Card(
            modifier = modifier
                .clip(MaterialTheme.shapes.large)
        ) {
            LazyRow(
                modifier = modifier
                    .padding(vertical = 12.dp)
            ) {
                items(weatherForecast.size) { index ->
                    ItemCardListSmall(
                        weatherForecast[index],
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCardListBig(
    weatherForecastDay: WeatherForDay,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            var color = MaterialTheme.colorScheme.primary
            if (weatherForecastDay.day.lowercase() != "today")
                color = MaterialTheme.colorScheme.tertiary
            Text(
                text = weatherForecastDay.day.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = color
            )
            Icon(
                painter = painterResource(id = weatherForecastDay.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
            Text(
                text = "${weatherForecastDay.temperatureMain}°",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,

                )
            Text(
                text = "${weatherForecastDay.temperatureLow}°",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun ItemCardListSmall(
    weatherForecastHour: WeatherForHour,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            var color = MaterialTheme.colorScheme.primary
            if (weatherForecastHour.time.toString().lowercase() != "now")
                color = MaterialTheme.colorScheme.tertiary
            Text(
                text = weatherForecastHour.time.toString().uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = color
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = weatherForecastHour.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
                Text(
                    text = "${weatherForecastHour.temperatureMain}°",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewAllLists(){
    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                CardListBig(weatherForDays)
                Spacer(modifier = Modifier.size(16.dp))
                CardListSmall(weatherForHours)
            }
        }
    }
}