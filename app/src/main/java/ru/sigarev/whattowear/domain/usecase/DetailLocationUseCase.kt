package ru.sigarev.whattowear.domain.usecase

import ru.sigarev.whattowear.domain.models.LocationWithWeather

interface DetailLocationUseCase {
    suspend fun fetchLocationWithWeather(uid: Int): LocationWithWeather
    suspend fun delete(uid: Int)
}