package com.leonardoalves.services_base

import com.leonardoalves.services_base.models.MovieListResponse
import io.reactivex.Observable

interface MovieDatabaseService {
    fun getPopularMovies(page: Int) : Observable<MovieListResponse>
    fun getUpcomingMovies(page: Int) : Observable<MovieListResponse>
    fun getTopRatedMovies(page: Int) : Observable<MovieListResponse>
}