package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class FeelsLikeResponse(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)