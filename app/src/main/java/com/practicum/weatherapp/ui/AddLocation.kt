package com.practicum.weatherapp.ui

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practicum.weatherapp.R
import com.practicum.weatherapp.Routes
import com.practicum.weatherapp.TopBar
import com.practicum.weatherapp.model.SavedLocation
import com.practicum.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddLocationScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherScreenViewModel = WeatherScreenViewModel(),
    navController: NavController
) {
    val weatherUiState by viewModel.uiState.collectAsState()
//    val searchLocation by viewModel.searchLocation.collectAsState()
    WeatherAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        title = "Add Location".uppercase(),
                        leftIcon = R.drawable.icon_close,
                        leftIconOnClick = { navController.navigate(Routes.LocationsScreen.route) },
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
                    var textState by remember { mutableStateOf(TextFieldValue("")) }
                    var displayText by remember { mutableStateOf("") }
                    Column() {
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            value = textState,
                            onValueChange = {
                                textState = it
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    displayText = textState.text
                                    viewModel.getSearchLocation(displayText)
                                }
                            ),
                            textStyle = TextStyle(
                                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                                fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                                fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                            decorationBox = { innerTextField ->
                                if (textState.text.isEmpty()) {
                                    Text(
                                        text = "Enter text here",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                }
                                innerTextField()
                            }
                        )
                        Divider(color = MaterialTheme.colorScheme.primary, thickness = 0.2.dp)
                    }
                    if (weatherUiState.seachingLocation != null) {
                        SearchingLocationItem(
                            searchedLocation = weatherUiState.seachingLocation!!,
                            onClick = { viewModel.saveLocation(weatherUiState.seachingLocation!!)}
                        )
                    }
                }
            }
        }
    }
}

fun getSubStrings(input: String): Pair<String, String> {
    if (input == "Not found") return Pair("Not found", "")
    val parts = input.split(",", limit = 2)
    return Pair(parts[0].trim(), if (parts.size > 1) parts[1].trim() else "")
}

@Composable
fun SearchingLocationItem(
    modifier: Modifier = Modifier,
    searchedLocation: SavedLocation,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = searchedLocation.getCity(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = searchedLocation.getCountry(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}