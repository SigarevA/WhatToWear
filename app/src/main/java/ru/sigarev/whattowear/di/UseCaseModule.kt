package ru.sigarev.whattowear.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sigarev.whattowear.domain.usecase.CreateLocationUseCase
import ru.sigarev.whattowear.domain.usecase.DetailLocationUseCase
import ru.sigarev.whattowear.domain.usecase.GetLocationsWithCurrentTemperature
import ru.sigarev.whattowear.domain.usecaseimpl.CreateLocationUseCaseImpl
import ru.sigarev.whattowear.domain.usecaseimpl.DetailLocationUseCaseImpl
import ru.sigarev.whattowear.domain.usecaseimpl.GetLocationsWithCurrentTemperatureImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindUseLocations(
        getLocationsWithCurrentTemperatureImpl: GetLocationsWithCurrentTemperatureImpl
    ): GetLocationsWithCurrentTemperature

    @Binds
    @Singleton
    internal abstract fun bindCreateLocationUseCase(
        createLocationUseCaseImpl: CreateLocationUseCaseImpl
    ): CreateLocationUseCase

    @Binds
    @Singleton
    abstract fun bindDetailLocationUseCase(
        detailLocationUseCaseImpl: DetailLocationUseCaseImpl
    ): DetailLocationUseCase
}