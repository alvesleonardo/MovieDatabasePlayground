package com.leonardoalves.services_base

import com.leonardoalves.domain.showroom.MovieShowRoom
import com.leonardoalves.services_base.models.MovieListResponse

object MovieShowRoomMapper {

    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    operator fun invoke(response: MovieListResponse) = with(response){
        this.results.orEmpty().map {
            MovieShowRoom(
                it.id,
                IMAGE_URL + it.posterPath,
                it.title ?: "",
                it.originalTitle ?: "",
                it.overview ?: ""
            )
        }
    }
}