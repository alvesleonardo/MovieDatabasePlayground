package com.leonardoalves.data_base

import com.leonardoalves.data_base.models.MovieListResponse
import io.reactivex.Observable

interface MovieShowroomService {
    fun getPopularMovies(page: Int) : Observable<MovieListResponse>
    fun getUpcomingMovies(page: Int) : Observable<MovieListResponse>
    fun getTopRatedMovies(page: Int) : Observable<MovieListResponse>
}