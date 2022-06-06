package ru.sigarev.whattowear.data.repositoriesImpl

import ru.sigarev.whattowear.BuildConfig
import ru.sigarev.whattowear.data.network.api.OpenWeatherApi
import ru.sigarev.whattowear.data.network.responses.WeatherResponse
import ru.sigarev.whattowear.domain.repositories.WeatherRepository

internal class WeatherRepositoryImpl(private val openWeatherApi: OpenWeatherApi) :
    WeatherRepository {
    override suspend fun fetchWeather(lon: Double, lat: Double): WeatherResponse =
        openWeatherApi.fetchWeather(lat, lon, BuildConfig.API_KEY)
}