package ru.sigarev.whattowear.domain.repositories

import ru.sigarev.whattowear.data.network.responses.WeatherResponse

interface WeatherRepository {
    suspend fun fetchWeather(lon: Double, lat: Double): WeatherResponse
}