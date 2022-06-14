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
    suspend fun insert(location: Location)

    @Query("UPDATE Location SET isFavorite = :isFavorite WHERE uid = :uid")
    suspend fun updateFavorite(uid: Int, isFavorite: Boolean)

    @Query("SELECT * FROM Location WHERE uid = :uid")
    suspend fun fetchLocation(uid: Int): Location

    @Query("DELETE FROM Location WHERE uid = :uid")
    suspend fun deleteById(uid: Int)
}