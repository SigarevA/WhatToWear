package ru.sigarev.whattowear.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sigarev.whattowear.domain.repositories.LocationRepository
import ru.sigarev.whattowear.domain.repositories.WeatherRepository
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperature
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperatureImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(
        locationRepository: LocationRepository,
        weatherRepository: WeatherRepository
    ): GetLocationsWithCurrentTemperature {
        return GetLocationsWithCurrentTemperatureImpl(
            locationRepository, weatherRepository
        )
    }
}