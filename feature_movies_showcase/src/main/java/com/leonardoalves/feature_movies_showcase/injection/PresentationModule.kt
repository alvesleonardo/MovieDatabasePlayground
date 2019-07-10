package com.leonardoalves.feature_movies_showcase.injection

import com.leonardoalves.feature_movies_showcase.movieList.MovieListPresenter
import com.leonardoalves.feature_movies_showcase.movieList.MovieListView
import org.koin.dsl.module

val presentationModule = module {
    factory { (view: MovieListView) -> MovieListPresenter(get(), view) }
}