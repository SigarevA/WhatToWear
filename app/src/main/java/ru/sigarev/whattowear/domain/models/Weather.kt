package ru.sigarev.whattowear.domain.models

data class Weather(
    val currentDt: Long,
    val currentTemperature: Int,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val mainWeather: String,
    val dayTemperature: DayTemperature,
    val icon: String
)