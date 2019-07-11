package com.leonardoalves.feature_movies_showcase.movieDetail

import com.leonardoalves.domain.detail.MovieDetailIterator
import io.reactivex.disposables.CompositeDisposable

class MovieDetailPresenter(private val movieDetailIterator: MovieDetailIterator,
                           private val view: MovieDetailView) {

    private val compositeDisposable = CompositeDisposable()

    fun getMovieData(movieId: Long){
        compositeDisposable.add(
            movieDetailIterator.getMovieDetail(movieId)
                .doOnSubscribe { view.startLoading() }
                .doOnComplete { view.stopLoading() }
                .subscribe({},{
                    it.printStackTrace()
                    view.stopLoading()
                },{})
        )
    }
}