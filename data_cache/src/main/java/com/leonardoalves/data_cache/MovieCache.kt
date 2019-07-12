package com.leonardoalves.data_cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MovieCache(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "homepage") val homepage: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster") val poster: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?
)

@Entity(tableName = "Movies_popular")
data class MovieCachePopular(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "homepage") val homepage: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster") val poster: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "position") val position: Int
)


@Entity(tableName = "Movies_upcoming")
data class MovieCacheUpcoming(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "homepage") val homepage: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster") val poster: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "position") val position: Int
)

@Entity(tableName = "Movies_top_rated")
data class MovieCacheTopRated(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "homepage") val homepage: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster") val poster: String?,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,
    @ColumnInfo(name = "position") val position: Int
)