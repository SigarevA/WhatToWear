package ru.sigarev.whattowear.data.db.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Location(
    @PrimaryKey val uid: Int = 0,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false
)