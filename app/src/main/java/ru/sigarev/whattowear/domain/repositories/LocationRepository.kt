package ru.sigarev.whattowear.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.sigarev.whattowear.data.db.objects.Location

interface LocationRepository {
    val locations: Flow<List<Location>>

    suspend fun save(location: Location)
    suspend fun updateIsFavorite(uid: Int, isFavorite: Boolean)
    suspend fun findLocationById(uid: Int): Location
    suspend fun deleteLocationById(uid : Int)
}