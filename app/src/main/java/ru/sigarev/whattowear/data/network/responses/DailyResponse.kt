package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DailyResponse(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    @SerialName("feels_like")
    val feelsLike: FeelsLikeResponse,
    val humidity: Int,
    @SerialName("moon_phase")
    val moonPhase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: TempResponse,
    val uvi: Double,
    val weather: List<WeatherXResponse>,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_gust")
    val windGust: Double,
    @SerialName("wind_speed")
    val windSpeed: Double,
)