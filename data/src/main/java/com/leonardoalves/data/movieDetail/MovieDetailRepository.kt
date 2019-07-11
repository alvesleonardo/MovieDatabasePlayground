package com.leonardoalves.data.movieDetail

import com.leonardoalves.data_base.MovieDetailMapper
import com.leonardoalves.data_base.MovieDetailService
import com.leonardoalves.domain.detail.FetchMovieDetail
import com.leonardoalves.domain.detail.MovieDetail
import io.reactivex.Observable

class MovieDetailRepository(private val movieDetailInfrastructure: MovieDetailService) : FetchMovieDetail {
    override fun getMovieDetail(id: Long): Observable<MovieDetail>  = movieDetailInfrastructure.getMovie(id).map { MovieDetailMapper(it) }
}