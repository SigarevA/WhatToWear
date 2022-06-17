package ru.sigarev.whattowear.domain.models

data class DayTemperature(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)