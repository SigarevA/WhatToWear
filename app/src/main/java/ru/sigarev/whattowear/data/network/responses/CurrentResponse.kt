package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(
    val humidity: Int,
    val pressure: Int,
    val dt: Int,
    val temp: Float,
    @SerialName("wind_speed")
    val windSpeed: Double,
    val weather: List<WeatherXResponse>
)