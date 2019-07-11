package com.leonardoalves.domain.detail

data class MovieDetail(
    val id: Long,
    val adult: Boolean,
    val homepage: String?,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val poster: String?,
    val vote_average: Double?
)