package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    // val alerts: List<AlertResponse>,
    val current: CurrentResponse,
    val daily: List<DailyResponse>,
    /*val hourly: List<HourlyResponse>,
    val minutely: List<MinutelyResponse>,
     */
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerialName("timezone_offset")
    val timezoneOffset: Int
)