package com.leonardoalves.testmoviedatabase.view.movieList

import com.leonardoalves.testmoviedatabase.module.IMAGE_URL
import com.leonardoalves.testmoviedatabase.repository.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import java.io.Serializable

class MovieListPresenter(
    private val repository: MovieRepository,
    private val view: MovieListView
) {
    private val compositeDisposable = CompositeDisposable()

    enum class Type : Serializable { UPCOMING, TOP_RATED, POPULAR }

    private var type = Type.POPULAR
    private var page = 1
    private var completed = false
    private var isLoading = false

    private val observable
        get() = when (type) {
            Type.UPCOMING -> repository.getUpcomingMovies(page)
            Type.POPULAR -> repository.getPopularMovies(page)
            Type.TOP_RATED -> repository.getTopRatedMovies(page)
        }

    fun onCreate() {
        getItems()
    }

    private fun getItems() {
        compositeDisposable.add(
            observable
                .doOnSubscribe {
                    isLoading = true
                    view.startLoading()
                }
                .doOnComplete {
                    isLoading = false
                    view.stopLoading()
                }
                .map { list ->
                    list.map { movie ->
                        MovieViewModel(
                            movie.id,
                            IMAGE_URL + movie.posterPath,
                            movie.title ?: "",
                            movie.overview ?: ""
                        )
                    }
                }
                .subscribe({
                    view.setItems(it, page == 1)
                    page++
                }, {
                    it.printStackTrace()
                    completed = true
                }, {})
        )
    }

    fun changeListType(newType: Type) {
        type = newType
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

    fun onDestroy(){
        compositeDisposable.clear()
    }
}