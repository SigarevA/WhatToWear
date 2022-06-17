package ru.sigarev.whattowear.domain.usecaseimpl

import ru.sigarev.whattowear.domain.models.Location
import ru.sigarev.whattowear.domain.models.LocationWithWeather
import ru.sigarev.whattowear.domain.repositories.LocationRepository
import ru.sigarev.whattowear.domain.repositories.WeatherRepository
import ru.sigarev.whattowear.domain.usecase.DetailLocationUseCase
import javax.inject.Inject

class DetailLocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : DetailLocationUseCase {
    override suspend fun fetchLocationWithWeather(uid: Int): LocationWithWeather {
        val location = locationRepository.findLocationById(uid)
        val weather = weatherRepository.fetchWeather(location.longitude, location.latitude)
        return LocationWithWeather(
            with(location) {
                Location(uid, name, latitude, longitude, isFavorite)
            },
            weather
        )
    }

    override suspend fun delete(uid: Int) {
        locationRepository.deleteLocationById(uid)
    }
}