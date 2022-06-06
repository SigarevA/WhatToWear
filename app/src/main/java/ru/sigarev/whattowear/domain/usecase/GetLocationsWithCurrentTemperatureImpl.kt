package ru.sigarev.whattowear.domain.usecase

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sigarev.whattowear.domain.models.LocationWithTemperature
import ru.sigarev.whattowear.domain.repositories.LocationRepository
import ru.sigarev.whattowear.domain.repositories.WeatherRepository

class GetLocationsWithCurrentTemperatureImpl(
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
                        locationInfo.uid,
                        locationInfo.name,
                        locationInfo.latitude,
                        locationInfo.longitude,
                        locationInfo.isFavorite,
                        weatherResponse.current.temp.toFloat()
                    )
                }
            }
    }
}