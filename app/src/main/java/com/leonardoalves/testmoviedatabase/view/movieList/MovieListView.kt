package com.leonardoalves.testmoviedatabase.view.movieList

interface MovieListView {
    fun setItems(itens: List<MovieViewModel>, resetList: Boolean = false)
    fun startLoading()
    fun stopLoading()

}