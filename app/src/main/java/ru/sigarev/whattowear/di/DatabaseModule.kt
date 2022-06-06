package ru.sigarev.whattowear.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sigarev.whattowear.data.db.AppDatabase
import ru.sigarev.whattowear.data.db.daos.LocationDAO
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "database-what-to-wear"
        ).build()

    @Singleton
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase): LocationDAO =
        appDatabase.locationDao()
}