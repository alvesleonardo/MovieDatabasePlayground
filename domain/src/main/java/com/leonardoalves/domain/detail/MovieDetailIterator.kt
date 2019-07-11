package com.leonardoalves.domain.detail

class MovieDetailIterator(private val movieDetailRepository: FetchMovieDetail) {
    fun getMovieDetail(id: Long) = movieDetailRepository.getMovieDetail(id)
}