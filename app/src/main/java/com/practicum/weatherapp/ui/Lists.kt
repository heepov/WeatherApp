package com.practicum.weatherapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
    weatherForecast: List<UiTimeForecast>,
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
    weatherForecastDay: UiDayForecast,
    modifier: Modifier = Modifier
) {
    WeatherAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            var color = MaterialTheme.colorScheme.primary
            if (weatherForecastDay.date.lowercase() != "today")
                color = MaterialTheme.colorScheme.tertiary
            Text(
                text = weatherForecastDay.date.uppercase(),
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
                text = "${weatherForecastDay.tempMax}°",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,

                )
            Text(
                text = "${weatherForecastDay.tempMin}°",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.tertiary
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
                    text = "${weatherForecastHour.temp}°",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

//@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun PreviewAllLists(){
//    WeatherAppTheme {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column (
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            ){
//                CardListBig(weatherForDays)
//                Spacer(modifier = Modifier.size(16.dp))
//                CardListSmall(weatherTodays)
//            }
//        }
//    }
//}