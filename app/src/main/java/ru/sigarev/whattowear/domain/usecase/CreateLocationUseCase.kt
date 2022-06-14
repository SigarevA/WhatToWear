package ru.sigarev.whattowear.domain.usecase

interface CreateLocationUseCase {
    suspend fun setLocation(latitude: Double, longitude: Double)
    suspend fun save(name: String)
}