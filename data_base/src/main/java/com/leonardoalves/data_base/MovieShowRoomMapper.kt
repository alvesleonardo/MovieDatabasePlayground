package com.leonardoalves.data_base

import com.leonardoalves.data_base.models.MovieListResponse
import com.leonardoalves.domain.showroom.MovieShowRoom

object MovieShowRoomMapper {

    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val PLACEHOLDER_URL = "https://europeixe.ch/wp-content/uploads/2019/05/no_image.png"

    operator fun invoke(response: MovieListResponse) = with(response) {
        this.results.orEmpty().map {
            val imageUrl = if (it.posterPath.isNullOrBlank()) {
                PLACEHOLDER_URL
            } else {
                IMAGE_URL + it.posterPath
            }
            MovieShowRoom(
                id = it.id,
                poster = imageUrl,
                localizedTitle = it.title ?: "",
                originalTitle = it.originalTitle ?: "",
                overview = it.overview ?: ""
            )
        }
    }
}