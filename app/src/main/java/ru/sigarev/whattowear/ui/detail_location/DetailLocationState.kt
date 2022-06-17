package ru.sigarev.whattowear.ui.detail_location

import ru.sigarev.whattowear.domain.models.LocationWithWeather

data class DetailLocationState(
    val uid: Int,
    val isLoading: Boolean = false,
    val content: LocationWithWeather? = null,
    val exception: Throwable? = null,
    val isRefresh: Boolean = false,
    val isDeletion: Boolean = false
) {
    val isSuccess: Boolean
        get() = content != null

    val isFailure: Boolean
        get() = exception != null
}