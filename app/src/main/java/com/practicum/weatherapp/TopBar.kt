package com.practicum.weatherapp

import android.content.res.Resources.Theme
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    @DrawableRes leftIcon: Int,
    @DrawableRes rightIcon: Int? = null,
    leftIconOnClick: () -> Unit = {},
    rightIconOnClick: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = leftIconOnClick) {
                Icon(
                    painter = painterResource(leftIcon),
                    contentDescription = null
                )
            }
        },
        actions = {
            if (rightIcon != null) {
                IconButton(onClick = rightIconOnClick) {
                    Icon(
                        painter = painterResource(id = rightIcon),
                        contentDescription = null
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .width(32.dp)
                )
            }
        }
    )
}