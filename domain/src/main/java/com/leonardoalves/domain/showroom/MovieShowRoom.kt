package com.leonardoalves.domain.showroom

data class MovieShowRoom(
    val id: Long,
    val poster: String,
    val localizedTitle: String,
    val originalTitle: String,
    val overview: String
)