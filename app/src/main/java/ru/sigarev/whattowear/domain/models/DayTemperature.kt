package ru.sigarev.whattowear.domain.models

data class DayTemperature(
    val day: Int,
    val eve: Int,
    val morn: Int,
    val night: Int
)