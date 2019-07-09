package com.leonardoalves.services_base

import com.leonardoalves.services_base.models.MovieResponse
import com.leonardoalves.services_base.models.MovieListResponse
import io.reactivex.Flowable

interface MovieDatabaseService {
    fun getPopularMovies(page: Int) : Flowable<MovieListResponse>
    fun getUpcomingMovies(page: Int) : Flowable<MovieListResponse>
    fun getTopRatedMovies(page: Int) : Flowable<MovieListResponse>
    fun getMovieData(movieId: Long) : Flowable<MovieResponse>
}