package ru.sigarev.whattowear.data.repositoriesImpl

import ru.sigarev.whattowear.BuildConfig
import ru.sigarev.whattowear.data.mappers.toModel
import ru.sigarev.whattowear.data.network.api.OpenWeatherApi
import ru.sigarev.whattowear.domain.models.Weather
import ru.sigarev.whattowear.domain.repositories.WeatherRepository

internal class WeatherRepositoryImpl(private val openWeatherApi: OpenWeatherApi) :
    WeatherRepository {
    override suspend fun fetchWeather(lon: Double, lat: Double): Weather =
        openWeatherApi.fetchWeather(lat, lon, BuildConfig.API_KEY).toModel()
}