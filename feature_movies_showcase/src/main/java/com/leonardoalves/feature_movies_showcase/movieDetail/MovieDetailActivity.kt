package com.leonardoalves.feature_movies_showcase.movieDetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.leonardoalves.domain.detail.MovieDetail
import com.leonardoalves.feature_common.utils.DialogUtils
import com.leonardoalves.feature_movies_showcase.R
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.math.RoundingMode
import java.text.NumberFormat

const val MOVIE_ID_EXTRA = "movie_id_extra"

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    private val presenter: MovieDetailPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, -1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(movieId > 0){
            presenter.getMovieData(movieId)
        } else {
            showCriticalError(R.string.error_movie_invalid)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }else -> super.onOptionsItemSelected(item)
    }

    override fun startLoading() {
    }

    override fun stopLoading() {
    }

    override fun setMovieData(movieDetail: MovieDetail){
        with(movieDetail){
            Glide.with(this@MovieDetailActivity)
                .load(poster)
                .into(ivPoster)
            this@MovieDetailActivity.title = title
            tvTitle.text = originalTitle
            tvOverview.text = overview
            val format = NumberFormat.getNumberInstance()
            format.roundingMode = RoundingMode.FLOOR
            format.maximumFractionDigits = 2
            tvRating.text = format.format(voteAverage)
        }
    }

    override fun showCriticalError(messageId: Int) {
        DialogUtils.showDialog(this, getString(messageId)){finish()}
    }
}