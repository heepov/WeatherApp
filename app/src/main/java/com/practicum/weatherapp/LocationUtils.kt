package com.practicum.weatherapp

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
suspend fun getLastKnownLocation(context: Context): Pair<Double, Double>? {
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    return try {
        val location = fusedLocationClient.lastLocation.await()
        if (location != null) {
            Pair(location.latitude, location.longitude)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}