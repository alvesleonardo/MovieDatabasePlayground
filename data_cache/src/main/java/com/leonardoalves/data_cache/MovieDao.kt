package com.leonardoalves.data_cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {
    @Query ("SELECT * FROM Movies")
    fun gelAll(): Flowable<List<MovieCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieCache: MovieCache) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieCache>) : Completable

    @Query("DELETE FROM Movies")
    fun clearMovies(): Completable
}