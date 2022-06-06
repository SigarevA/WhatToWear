package ru.sigarev.whattowear.domain.models

class LocationWithTemperature(
    val uid: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean,
    val currentTemperature: Float
)