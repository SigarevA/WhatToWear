package ru.sigarev.whattowear.domain.usecaseimpl

import ru.sigarev.whattowear.data.db.objects.Location
import ru.sigarev.whattowear.domain.models.LocationPoint
import ru.sigarev.whattowear.domain.repositories.LocationRepository
import ru.sigarev.whattowear.domain.usecase.CreateLocationUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class CreateLocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : CreateLocationUseCase {
    private var point: LocationPoint by Delegates.notNull<LocationPoint>()

    override suspend fun setLocation(latitude: Double, longitude: Double) {
        point = LocationPoint(latitude, longitude)
    }

    override suspend fun save(name: String) {
        locationRepository.save(
            Location(
                name = name,
                latitude = point.lat,
                longitude = point.lon
            )
        )
    }
}