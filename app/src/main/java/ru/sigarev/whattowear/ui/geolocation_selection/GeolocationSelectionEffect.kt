package ru.sigarev.whattowear.ui.geolocation_selection

sealed class GeolocationSelectionEffect {
    class MoveToLocation(val lat: Double, val lon: Double) : GeolocationSelectionEffect()
}
