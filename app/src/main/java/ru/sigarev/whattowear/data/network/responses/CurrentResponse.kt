package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(
    /*val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
     */
    val humidity: Int,
    val pressure: Int,
    val dt: Int,
    val temp: Float,
    @SerialName("wind_speed")
    val windSpeed: Double,
    val weather: List<WeatherXResponse>
    /*val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherXResponse>,
    val wind_deg: Int,
    val wind_speed: Double
     */
)