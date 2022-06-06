package ru.sigarev.whattowear.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sigarev.whattowear.data.db.daos.LocationDAO
import ru.sigarev.whattowear.data.network.api.OpenWeatherApi
import ru.sigarev.whattowear.data.repositoriesImpl.LocationRepositoryImpl
import ru.sigarev.whattowear.data.repositoriesImpl.WeatherRepositoryImpl
import ru.sigarev.whattowear.domain.repositories.LocationRepository
import ru.sigarev.whattowear.domain.repositories.WeatherRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideLocationRepository(locationDAO: LocationDAO): LocationRepository =
        LocationRepositoryImpl(locationDAO)

    @Provides
    @Singleton
    fun provideWeatherRepository(openWeatherApi: OpenWeatherApi): WeatherRepository =
        WeatherRepositoryImpl(openWeatherApi)
}