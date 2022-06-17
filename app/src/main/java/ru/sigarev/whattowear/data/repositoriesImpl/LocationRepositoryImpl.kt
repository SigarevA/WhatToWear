package ru.sigarev.whattowear.data.repositoriesImpl

import kotlinx.coroutines.flow.Flow
import ru.sigarev.whattowear.data.db.daos.LocationDAO
import ru.sigarev.whattowear.data.db.objects.Location
import ru.sigarev.whattowear.domain.repositories.LocationRepository

internal class LocationRepositoryImpl(private val locationDAO: LocationDAO) : LocationRepository {
    override val locations: Flow<List<Location>>
        get() = locationDAO.getAll()

    override suspend fun save(location: Location) {
        locationDAO.insert(location)
    }

    override suspend fun updateIsFavorite(uid: Int, isFavorite: Boolean) {
        locationDAO.updateFavorite(uid, isFavorite)
    }

    override suspend fun findLocationById(uid: Int): Location =
        locationDAO.fetchLocation(uid)

    override suspend fun deleteLocationById(uid: Int) {
        locationDAO.deleteById(uid)
    }
}