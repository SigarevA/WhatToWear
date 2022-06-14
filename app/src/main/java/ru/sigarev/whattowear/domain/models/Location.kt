package ru.sigarev.whattowear.domain.models

data class Location(
    val uid: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean,
)