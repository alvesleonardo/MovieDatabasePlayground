package com.leonardoalves.feature_movies_showcase.recyclerview

import com.leonardoalves.domain.showroom.MovieShowRoom
import com.leonardoalves.feature_common.custom.RecyclerViewModel

class MovieViewModel(
    val id: Long,
    val picture: String,
    val title: String,
    val description: String
) : RecyclerViewModel {
    constructor(movieShowRoom: MovieShowRoom) : this(
        movieShowRoom.id,
        movieShowRoom.poster,
        movieShowRoom.localizedTitle,
        movieShowRoom.overview
    )
}