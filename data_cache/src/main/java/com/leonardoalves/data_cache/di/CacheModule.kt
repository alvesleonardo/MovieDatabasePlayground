package com.leonardoalves.data_cache.di

import androidx.room.Room
import com.leonardoalves.data_cache.CacheDatabase
import com.leonardoalves.data_cache.MovieDao
import org.koin.dsl.module

val cacheModule = module {
    single { Room.databaseBuilder(get(), CacheDatabase::class.java, "database-cache-movie").build() }
    single { get<CacheDatabase>().movieDao() }
    single { get<CacheDatabase>().movieShowroomDao() }
}