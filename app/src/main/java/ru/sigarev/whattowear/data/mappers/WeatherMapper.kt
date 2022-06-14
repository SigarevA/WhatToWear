package ru.sigarev.whattowear.data.mappers

import ru.sigarev.whattowear.data.network.responses.WeatherResponse
import ru.sigarev.whattowear.domain.models.DayTemperature
import ru.sigarev.whattowear.domain.models.Weather

fun WeatherResponse.toModel() =
    Weather(
        this.current.dt * 1000L,
        this.current.temp,
        this.current.humidity,
        this.current.pressure,
        this.current.windSpeed,
        this.current.weather[0].main,
        with(this.daily[0].temp) {
            DayTemperature(
                day,
                eve,
                morn,
                night
            )
        }
    )