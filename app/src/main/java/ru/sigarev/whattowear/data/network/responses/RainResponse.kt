package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
class RainResponse(
    val `1h`: Double
)