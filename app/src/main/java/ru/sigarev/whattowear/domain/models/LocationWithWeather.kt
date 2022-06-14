package ru.sigarev.whattowear.domain.models

data class LocationWithWeather(
    val location: Location,
    val weather: Weather
)