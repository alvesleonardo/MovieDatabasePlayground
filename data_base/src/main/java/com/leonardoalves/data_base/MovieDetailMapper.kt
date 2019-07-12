package com.leonardoalves.data_base

import com.leonardoalves.data_base.models.MovieDetailResponse
import com.leonardoalves.domain.detail.MovieDetail

object MovieDetailMapper {

    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    operator fun invoke(response: MovieDetailResponse) = with(response) {
        MovieDetail(
            id = id,
            adult = adult ?: false,
            homepage = homepage,
            title = title ?: "",
            originalTitle = originalTitle ?: "",
            overview = overview ?: "",
            poster = if(!posterPath.isNullOrBlank()) IMAGE_URL+posterPath else null,
            voteAverage = voteAverage
        )
    }
}