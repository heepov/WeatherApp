package com.practicum.weatherapp.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.weatherapp.R

@Composable
fun RectangleCardSmall(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(colorScheme.surface)
            .aspectRatio(1f / 0.45f)
            .padding(16.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.surface)
        ) {
            Text(
                text = title.uppercase(),
                style = typography.titleLarge,
                color = colorScheme.primary
            )
            Text(
                text = description,
                style = typography.titleMedium,
                color = colorScheme.primary
            )
        }
    }
}

@Composable
fun RectangleCardWithDescription(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @DrawableRes icon: Int,
    value: Int,
    description: String
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(colorScheme.background)
            .aspectRatio(1f / 1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = title).uppercase(),
                style = typography.titleMedium,
                color = colorScheme.primary
            )
            Text(
                text = description,
                style = typography.titleLarge,
                color = colorScheme.primary
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = value.toString(),
                style = typography.bodyLarge,
                color = colorScheme.primary,
            )
        }
    }
}


@Composable
fun RectangleCardWithUnit(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @DrawableRes icon: Int = R.drawable.humidity,
    value: Int,
    @StringRes unit: Int,
    direction: Float? = null,
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(colorScheme.background)
            .aspectRatio(1f / 1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = title).uppercase(),
                style = typography.titleMedium,
                color = colorScheme.primary
            )
            if (direction != null) {
                WindDirectionIcon(
                    direction = direction,
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = colorScheme.primary,
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = value.toString(),
                    style = typography.bodyLarge,
                    color = colorScheme.primary,
                    modifier = Modifier
                        .alignByBaseline()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = unit).uppercase(),
                    lineHeight = 20.sp,
                    style = typography.labelLarge,
                    fontSize = 8.sp,
                    color = colorScheme.primary,
                    modifier = Modifier
                        .alignByBaseline()
                )
            }
        }
    }
}

@Composable
fun RectangleCardSimple(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @DrawableRes icon: Int,
    value: String,
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(colorScheme.background)
            .aspectRatio(1f / 1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = title).uppercase(),
                style = typography.titleMedium,
                color = colorScheme.primary
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = value,
                style = typography.bodyLarge,
                color = colorScheme.primary,
            )
        }
    }
}

@Composable
fun WindDirectionIcon(
    modifier: Modifier = Modifier,
    direction: Float,
){
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.wind),
            contentDescription = null,
            tint = colorScheme.primary,
            modifier = Modifier
                .fillMaxSize()
        )
        Icon(
            painter = painterResource(id = R.drawable.wind_arrow),
            contentDescription = null,
            tint = colorScheme.primary,
            modifier = Modifier
                .fillMaxSize()
                .rotate(direction)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun RectangleCardWindPreview(){
    Row() {
        RectangleCardWithUnit(
            title = R.string.humidity,
            icon = R.drawable.humidity,
            value = 23,
            unit = R.string.percent,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(16.dp))
        RectangleCardWithUnit(
            title = R.string.wind_speed,
            value = 12,
            unit = R.string.wind_unit_metric,
            direction = 270.0f,
            modifier = Modifier.weight(1f)
        )
    }

}