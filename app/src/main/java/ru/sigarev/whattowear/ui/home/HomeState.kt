package ru.sigarev.whattowear.ui.home

data class HomeState(
    val filter: LocationFilter = LocationFilter.All
)

enum class LocationFilter {
    All, Favorites
}