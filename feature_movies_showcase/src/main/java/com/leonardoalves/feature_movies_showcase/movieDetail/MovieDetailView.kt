package com.leonardoalves.feature_movies_showcase.movieDetail

import com.leonardoalves.domain.detail.MovieDetail

interface MovieDetailView {
    fun startLoading()
    fun stopLoading()
    fun setMovieData(movieDetail: MovieDetail)
    fun showCriticalError(messageId: Int)
}