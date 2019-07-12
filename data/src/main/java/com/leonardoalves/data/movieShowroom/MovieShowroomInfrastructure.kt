package com.leonardoalves.data.movieShowroom

import com.leonardoalves.data.api.MovieDatabaseV4Api
import com.leonardoalves.data.utils.ExecutionErrorHandler
import com.leonardoalves.data.utils.onDefaultSchedulers
import com.leonardoalves.data_base.MovieShowroomService
import com.leonardoalves.data_base.models.MovieListResponse
import io.reactivex.Observable

class MovieShowroomInfrastructure(
    private val api: MovieDatabaseV4Api,
    private val executionErrorHandler: ExecutionErrorHandler<MovieListResponse>
) : MovieShowroomService {

    override fun getPopularMovies(page: Int): Observable<MovieListResponse> = api.getPopular(page)
        .onDefaultSchedulers()
        .compose(executionErrorHandler)


    override fun getUpcomingMovies(page: Int): Observable<MovieListResponse> {
        return api.getUpcoming(page)
            .onDefaultSchedulers()
            .compose(executionErrorHandler)

    }

    override fun getTopRatedMovies(page: Int): Observable<MovieListResponse> {
        return api.getTopRated(page)
            .onDefaultSchedulers()
            .compose(executionErrorHandler)
    }
}