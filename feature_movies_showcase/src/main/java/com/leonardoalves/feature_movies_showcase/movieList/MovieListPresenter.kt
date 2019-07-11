package com.leonardoalves.feature_movies_showcase.movieList

import com.leonardoalves.domain.showroom.FetchMoviesList
import com.leonardoalves.domain.showroom.MovieShowroomIterator
import com.leonardoalves.feature_movies_showcase.R
import com.leonardoalves.feature_movies_showcase.recyclerview.MovieViewModel
import io.reactivex.disposables.CompositeDisposable

class MovieListPresenter(
    private val movieShowroomIterator: MovieShowroomIterator,
    private val view: MovieListView
) {
    private val compositeDisposable = CompositeDisposable()


    private var type = FetchMoviesList.Type.POPULAR
    private var page = 1
    private var completed = false
    private var isLoading = false

    fun onCreate() {
        getItems()
    }

    private fun getItems() {
        compositeDisposable.add(
            movieShowroomIterator.getList(page, type)
                .doOnSubscribe {
                    isLoading = true
                    view.startLoading()
                }
                .doOnComplete {
                    isLoading = false
                    view.stopLoading()
                }
                .map { movies -> movies.map { MovieViewModel(it) } }
                .subscribe({
                    view.setItems(it, page == 1)
                    page++
                }, {
                    it.printStackTrace()
                    completed = true
                    view.stopLoading()
                }, {})
        )
    }

    fun changeListType(newType: Int) {
        type = when (newType) {
            R.id.action_popular_movies -> FetchMoviesList.Type.POPULAR
            R.id.action_upcoming_movies -> FetchMoviesList.Type.UPCOMING
            R.id.action_top_rated_movies -> FetchMoviesList.Type.TOP_RATED
            else -> FetchMoviesList.Type.POPULAR
        }
        refreshList()
    }

    private fun refreshList() {
        page = 1
        completed = false
        compositeDisposable.clear()
        isLoading = false
        view.setItems(listOf(), true)
        getItems()
    }

    fun onScrollBeyond() {
        if (!isLoading && !completed) {
            getItems()
        }
    }

    fun onRefresh() {
        refreshList()
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}