package com.leonardoalves.data_cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Query ("SELECT * FROM Movies WHERE uid = :uid")
    fun get(uid: Long): Observable<MovieCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieCache: MovieCache) : Completable

    @Query("DELETE FROM Movies")
    fun clearMovies(): Completable
}