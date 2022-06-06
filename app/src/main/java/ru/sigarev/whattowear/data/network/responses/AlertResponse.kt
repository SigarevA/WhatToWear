package ru.sigarev.whattowear.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class AlertResponse(
    val description: String,
    val end: Int,
    val event: String,
    val sender_name: String,
    val start: Int,
    val tags: List<String>
)