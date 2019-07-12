package com.leonardoalves.feature_movies_showcase.movieList

import com.leonardoalves.feature_movies_showcase.recyclerview.MovieViewModel

interface MovieListView {
    fun setItems(itens: List<MovieViewModel>, resetList: Boolean = false)
    fun startLoading()
    fun stopLoading()
    fun showNetworkError(message: Int)
    fun showGenericError(message: String)

}