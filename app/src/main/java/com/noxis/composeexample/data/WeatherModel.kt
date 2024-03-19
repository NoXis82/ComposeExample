package com.noxis.composeexample.data

data class WeatherModel(
    val city: String = "",
    val time: String = "",
    val currentTempC: String = "",
    val condition: String = "",
    val icon: String = "",
    val maxTempC: String = "",
    val minTempC: String = "",
    val hours: String = ""
)
