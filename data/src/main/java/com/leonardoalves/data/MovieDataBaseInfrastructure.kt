package com.leonardoalves.data

import com.leonardoalves.data.utils.onDefaultSchedulers
import com.leonardoalves.services_base.MovieDatabaseService
import com.leonardoalves.services_base.models.MovieListResponse
import io.reactivex.Observable

class MovieDataBaseInfrastructure(
    private val api: MovieDatabaseService,
    private val executionErrorHandler: ExecutionErrorHandler<MovieListResponse>
) : MovieDatabaseService {

    override fun getPopularMovies(page: Int) = api.getPopularMovies(page)
        .onDefaultSchedulers()
        .compose(executionErrorHandler)


    override fun getUpcomingMovies(page: Int): Observable<MovieListResponse> {
        return api.getUpcomingMovies(page)
            .onDefaultSchedulers()
            .compose(executionErrorHandler)

    }

    override fun getTopRatedMovies(page: Int): Observable<MovieListResponse> {
        return api.getTopRatedMovies(page)
            .onDefaultSchedulers()
            .compose(executionErrorHandler)
    }
}