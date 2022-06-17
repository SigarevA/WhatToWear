package ru.sigarev.whattowear.domain.repositories

import ru.sigarev.whattowear.data.network.responses.WeatherResponse
import ru.sigarev.whattowear.domain.models.Weather

interface WeatherRepository {
    suspend fun fetchWeather(lon: Double, lat: Double): Weather
}