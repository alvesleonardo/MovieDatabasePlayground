package com.leonardoalves.data_base

import com.leonardoalves.domain.showroom.MovieShowRoom
import com.leonardoalves.data_base.models.MovieListResponse

object MovieShowRoomMapper {

    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    operator fun invoke(response: MovieListResponse) = with(response){
        this.results.orEmpty().map {
            val imageUrl = if (it.posterPath.isNullOrBlank()){ "https://europeixe.ch/wp-content/uploads/2019/05/no_image.png"} else {IMAGE_URL + it.posterPath}
            MovieShowRoom(
                it.id,
                imageUrl,
                it.title ?: "",
                it.originalTitle ?: "",
                it.overview ?: ""
            )
        }
    }
}