package com.example.geliculon

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}