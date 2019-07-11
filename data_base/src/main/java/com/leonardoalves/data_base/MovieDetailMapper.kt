package com.leonardoalves.data_base

import com.leonardoalves.data_base.models.MovieDetailResponse
import com.leonardoalves.domain.detail.MovieDetail

object MovieDetailMapper {
    operator fun invoke(response: MovieDetailResponse) = with(response) {
        MovieDetail(
            id = id,
            adult = adult ?: false,
            homepage = homepage,
            title = title ?: "",
            originalTitle = originalTitle ?: "",
            overview = overview ?: "",
            poster = posterPath,
            vote_average = voteAverage
        )
    }
}