package com.leonardoalves.data_cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieCache::class], version = 1)
abstract class CacheDatabase : RoomDatabase(){
    abstract fun movieDao():MovieDao
}