package ru.sigarev.whattowear.ui.home

import ru.sigarev.whattowear.domain.models.LocationWithTemperature

data class HomeState(
    val filter: LocationFilter = LocationFilter.All,
    val locationsWithTemperature: List<LocationWithTemperature>? = null
)

enum class LocationFilter {
    All, Favorites
}