package ru.sigarev.whattowear.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.sigarev.whattowear.domain.models.LocationWithTemperature

interface GetLocationsWithCurrentTemperature {
    fun fetch(): Flow<List<LocationWithTemperature>>
    suspend fun changeFavorite(uid: Int, isFavorite: Boolean)
}