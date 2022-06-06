package ru.sigarev.whattowear.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.sigarev.whattowear.data.db.objects.Location

@Dao
interface LocationDAO {
    @Query("SELECT * FROM Location")
    fun getAll(): Flow<List<Location>>

    @Insert
    fun insert(location: Location)
}