package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
class TempResponse(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)