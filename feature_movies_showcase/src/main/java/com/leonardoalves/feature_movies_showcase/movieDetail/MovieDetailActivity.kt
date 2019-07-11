package com.leonardoalves.feature_movies_showcase.movieDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

const val MOVIE_ID_EXTRA = "movie_id_extra"

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    private val presenter: MovieDetailPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, -1)
        if(movieId > 0){
            presenter.getMovieData(movieId)
        }
    }

    override fun startLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}