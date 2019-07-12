package com.leonardoalves.data_cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieShowroomDao {
    @Query("SELECT * FROM Movies_popular ORDER BY position")
    fun getPopulars(): Observable<List<MovieCachePopular>>

    @Query("SELECT * FROM Movies_upcoming ORDER BY position")
    fun getUpcoming(): Observable<List<MovieCacheUpcoming>>

    @Query("SELECT * FROM Movies_top_rated ORDER BY position")
    fun getTopRated(): Observable<List<MovieCacheTopRated>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPopulars(movies: List<MovieCachePopular>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUpcoming(movies: List<MovieCacheUpcoming>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTopRated(movies: List<MovieCacheTopRated>) : Completable
}