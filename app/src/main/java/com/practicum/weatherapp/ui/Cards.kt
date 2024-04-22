package com.practicum.weatherapp.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import com.practicum.weatherapp.R
import com.practicum.weatherapp.model.WeatherRepository
import com.practicum.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CircleCard(
    modifier: Modifier = Modifier,
    description: String?,
    temperatureMain: Int,
    temperatureHigh: Int?,
    temperatureLow: Int?,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .aspectRatio(1f / 1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (description != null)
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            Text(
                text = "$temperatureMain°",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            if (temperatureHigh != null && temperatureLow != null) {
                Row {
                    Text(
                        text = "H: $temperatureHigh °",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "L : $temperatureLow °",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary

                    )
                }
            }
        }
    }
}

@Composable
fun RectangleCardSmall(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.background)
            .aspectRatio(1f / 0.45f)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = description.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun RectangleCardBig(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    @DrawableRes icon: Int,
    value: String,
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.background)
            .aspectRatio(1f / 1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            if (description != null)
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            val extraText = when (title.lowercase()) {
                "humidity" -> "%"
                "wind speed" -> "M/S"
                else -> ""
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .alignByBaseline()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = extraText,
                    lineHeight = 20.sp,
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .alignByBaseline()
                )
            }
        }
    }
}

@Composable
fun OneRow(
    modifier: Modifier = Modifier,
    spacerSize: Int = 16,
    first: @Composable () -> Unit,
    second: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        first()
        Spacer(modifier = Modifier.size(spacerSize.dp))
        second()
    }
}

@Composable
fun OneBlock(
    modifier: Modifier = Modifier,
    spacerSize: Int = 16,
    first: @Composable () -> Unit,
    second: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        first()
        Spacer(modifier = Modifier.size(spacerSize.dp))
        second()
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewAllCards() {
    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                OneRow(
                    first = {
                        CircleCard(
                            description = "Feels Like",
                            temperatureMain = 20,
                            temperatureHigh = 32,
                            temperatureLow = -2,
                            modifier = Modifier
                                .weight(1f)
                        )
                    },
                    second = {
                        OneBlock(
                            modifier = Modifier.weight(1f),
                            first = {
                                RectangleCardSmall(
                                    description = "Feels Like",
                                    title = "Temperature",
                                    modifier = Modifier
                                )
                            },
                            second = {
                                RectangleCardSmall(
                                    description = "Feels Like",
                                    title = "Temperature",
                                    modifier = Modifier
                                )
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                OneRow(
                    first = {
                        RectangleCardBig(
                            title = "Humidity",
                            description = "Humidity",
                            icon = R.drawable.ic_launcher_foreground,
                            value = "70",
                            modifier = Modifier.weight(1f)
                        )
                    },
                    second = {
                        RectangleCardBig(
                            title = "Humidity",
                            description = "Humidity",
                            icon = R.drawable.ic_launcher_foreground,
                            value = "70",
                            modifier = Modifier.weight(1f)
                        )
                    }
                )
            }
        }
    }
}