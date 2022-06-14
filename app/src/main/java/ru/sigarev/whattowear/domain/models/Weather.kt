package ru.sigarev.whattowear.domain.models

data class Weather(
    val currentDt: Long,
    val currentTemperature: Float,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val mainWeather: String,
    val dayTemperature: DayTemperature
)