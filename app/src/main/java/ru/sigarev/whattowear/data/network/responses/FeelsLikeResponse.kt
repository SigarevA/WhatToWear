package ru.sigarev.whattowear.data.network.responses

data class FeelsLikeResponse(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)