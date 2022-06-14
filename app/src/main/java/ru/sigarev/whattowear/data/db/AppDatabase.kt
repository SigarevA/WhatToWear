package ru.sigarev.whattowear.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sigarev.whattowear.data.db.daos.LocationDAO
import ru.sigarev.whattowear.data.db.objects.Location

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDAO
}