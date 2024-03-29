package com.leonardoalves.data_base.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("overview")
	val overview: String? = null,

    @field:SerializedName("original_language")
	val originalLanguage: String? = null,

    @field:SerializedName("original_title")
	val originalTitle: String? = null,

    @field:SerializedName("video")
	val video: Boolean? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("genre_ids")
	val genreIds: List<Int?>? = null,

    @field:SerializedName("poster_path")
	val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

    @field:SerializedName("release_date")
	val releaseDate: String? = null,

    @field:SerializedName("media_type")
	val mediaType: String? = null,

    @field:SerializedName("popularity")
	val popularity: Double? = null,

    @field:SerializedName("voteAverage")
	val voteAverage: Double? = null,

    @field:SerializedName("id")
	val id: Long = 0L,

    @field:SerializedName("adult")
	val adult: Boolean? = null,

    @field:SerializedName("vote_count")
	val voteCount: Int? = null
)