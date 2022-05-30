package ru.sigarev.whattowear.data.network.responses

class DailyResponse(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLikeResponse,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: TempResponse,
    val uvi: Double,
    val weather: List<WeatherXResponse>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)