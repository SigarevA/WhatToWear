package ru.sigarev.whattowear.data.mappers

import ru.sigarev.whattowear.data.network.responses.WeatherResponse
import ru.sigarev.whattowear.domain.models.DayTemperature
import ru.sigarev.whattowear.domain.models.Weather
import kotlin.math.roundToInt

fun WeatherResponse.toModel() =
    Weather(
        this.current.dt * 1000L,
        this.current.temp.roundToInt(),
        this.current.humidity,
        this.current.pressure,
        this.current.windSpeed,
        this.current.weather[0].main,
        with(this.daily[0].temp) {
            DayTemperature(
                day.roundToInt(),
                eve.roundToInt(),
                morn.roundToInt(),
                night.roundToInt()
            )
        },
        current.weather[0].icon
    )