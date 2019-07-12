package com.leonardoalves.data_cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieCache::class, MovieCacheUpcoming::class, MovieCacheTopRated::class, MovieCachePopular::class], version = 1)
abstract class CacheDatabase : RoomDatabase(){
    abstract fun movieDao():MovieDao
    abstract fun movieShowroomDao(): MovieShowroomDao
}