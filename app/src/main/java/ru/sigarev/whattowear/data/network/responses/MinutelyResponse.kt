package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
class MinutelyResponse(
    val dt: Int,
    val precipitation: Int
)