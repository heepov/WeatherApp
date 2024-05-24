package com.practicum.weatherapp.ui

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.weatherapp.R
import com.practicum.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddLocationScreen(
    viewModel: WeatherScreenViewModel = WeatherScreenViewModel(),
    modifier: Modifier = Modifier,
) {
    val weatherUiState by viewModel.uiState.collectAsState()

    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    val layoutDirection = LocalLayoutDirection.current
                    AppBar(
                        place = "Locations".uppercase(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateStartPadding(layoutDirection),
                                end = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateEndPadding(layoutDirection),
                            ),
                        iconLeft = R.drawable.icon_back,
                        iconRight = R.drawable.icon_pluse,
                        iconRightOnClick = { TODO() },
                        iconLeftOnClick = { (LocalContext.current as? Activity)?.finish() },
                    )
                }
            ) { contentPadding ->
                Column(
                    modifier = modifier
                        .padding(contentPadding)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 32.dp)
                        .padding(top = 32.dp)
                ) {
                    var searchedLocation: Pair<String, String>
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface),
                        value = "Search",
                        onValueChange = {
//                            FindLocationItem(
//                                searchedLocation = getSubStrings(viewModel.getSearchLocationsList(it))
//                            )
                        })
                }
            }
        }
    }
}

fun getSubStrings(input: String): Pair<String, String> {
    if(input == "Not found") return Pair("Not found", "")
    val parts = input.split(",", limit = 2)
    return Pair(parts[0].trim(), if (parts.size > 1) parts[1].trim() else "")
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FindLocationItem(
    modifier: Modifier = Modifier,
    searchedLocation: Pair<String, String>,
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = searchedLocation.first,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = searchedLocation.second,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}