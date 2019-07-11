package com.leonardoalves.data.movieDetail

import com.leonardoalves.data.api.MovieDatabaseV3Api
import com.leonardoalves.data.utils.ExecutionErrorHandler
import com.leonardoalves.data.utils.onDefaultSchedulers
import com.leonardoalves.data_base.MovieDetailService
import com.leonardoalves.data_base.models.MovieDetailResponse
import io.reactivex.Observable

class MovieDetailInfrastructure(
    private val api: MovieDatabaseV3Api,
    private val executionErrorHandler: ExecutionErrorHandler<MovieDetailResponse>
) : MovieDetailService {
    override fun getMovie(id: Long): Observable<MovieDetailResponse> =
        api.getMovieDetail(id)
            .onDefaultSchedulers()
            .compose(executionErrorHandler)
}