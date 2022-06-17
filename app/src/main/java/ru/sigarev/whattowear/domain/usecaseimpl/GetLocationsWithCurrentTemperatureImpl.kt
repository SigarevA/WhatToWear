package ru.sigarev.whattowear.domain.usecaseimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.sigarev.whattowear.domain.models.Location
import ru.sigarev.whattowear.domain.models.LocationWithTemperature
import ru.sigarev.whattowear.domain.repositories.LocationRepository
import ru.sigarev.whattowear.domain.repositories.WeatherRepository
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperature
import javax.inject.Inject

class GetLocationsWithCurrentTemperatureImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : GetLocationsWithCurrentTemperature {
    override fun fetch(): Flow<List<LocationWithTemperature>> {
        return locationRepository.locations
            .map { locations ->
                val requests = withContext(Dispatchers.IO) {
                    locations.map { location ->
                        async {
                            weatherRepository.fetchWeather(
                                location.longitude,
                                location.latitude
                            )
                        }
                    }
                }
                requests.awaitAll().mapIndexed { index, weatherResponse ->
                    val locationInfo = locations[index]
                    LocationWithTemperature(
                        Location(
                            locationInfo.uid,
                            locationInfo.name,
                            locationInfo.latitude,
                            locationInfo.longitude,
                            locationInfo.isFavorite,
                        ),
                        weatherResponse.currentTemperature
                    )
                }
            }
    }

    override suspend fun changeFavorite(uid: Int, isFavorite: Boolean) {
        locationRepository.updateIsFavorite(uid, isFavorite)
    }
}