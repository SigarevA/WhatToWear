package ru.sigarev.whattowear.domain.models

data class LocationWithTemperature(
    val location: Location,
    val currentTemperature: Int
)