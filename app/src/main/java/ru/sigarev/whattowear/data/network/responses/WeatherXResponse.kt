package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
class WeatherXResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)