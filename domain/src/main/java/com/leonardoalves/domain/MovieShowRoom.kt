package com.leonardoalves.domain

data class MovieShowRoom(
    val id: Long,
    val poster: String,
    val localizedTitle: String,
    val originalTitle: String,
    val overview: String
)