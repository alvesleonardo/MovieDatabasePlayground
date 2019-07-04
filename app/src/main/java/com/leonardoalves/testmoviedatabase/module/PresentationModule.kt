package com.leonardoalves.testmoviedatabase.module

import com.leonardoalves.testmoviedatabase.view.movieList.MovieListPresenter
import com.leonardoalves.testmoviedatabase.view.movieList.MovieListView
import org.koin.dsl.module

val presentationModule = module {
    factory { (view: MovieListView) -> MovieListPresenter(get(), view) }
}