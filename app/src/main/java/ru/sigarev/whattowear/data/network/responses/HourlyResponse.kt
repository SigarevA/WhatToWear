package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
class HourlyResponse(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: RainResponse,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherXResponse>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)