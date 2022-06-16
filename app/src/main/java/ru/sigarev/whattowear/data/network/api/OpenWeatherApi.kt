package ru.sigarev.whattowear.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.sigarev.whattowear.data.network.responses.WeatherResponse

interface OpenWeatherApi {
    @GET("onecall")
    suspend fun fetchWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units : String
    ) : WeatherResponse

}