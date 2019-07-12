package com.leonardoalves.feature_movies_showcase.movieList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.leonardoalves.feature_common.custom.*
import com.leonardoalves.feature_movies_showcase.R
import com.leonardoalves.feature_movies_showcase.movieDetail.MOVIE_ID_EXTRA
import com.leonardoalves.feature_movies_showcase.movieDetail.MovieDetailActivity
import com.leonardoalves.feature_movies_showcase.recyclerview.MovieViewHolder
import com.leonardoalves.feature_movies_showcase.recyclerview.MovieViewHolder.Companion.MOVIE_LIST_LAYOUT_ID
import com.leonardoalves.feature_movies_showcase.recyclerview.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MovieListActivity : AppCompatActivity(), MovieListView {

    private val presenter: MovieListPresenter by inject { parametersOf(this) }

    private lateinit var movieAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
        presenter.onCreate()
    }

    private fun setupList() {
        movieAdapter = RecyclerViewAdapter(object : ViewHolderFactory {
            override fun getType(recyclerViewModel: RecyclerViewModel): Int = when (recyclerViewModel) {
                is MovieViewModel -> MOVIE_LIST_LAYOUT_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View) = when (viewType) {
                MOVIE_LIST_LAYOUT_ID -> MovieViewHolder(
                    view,
                    onMovieClicked
                )
                else -> throw IllegalArgumentException()
            }
        })
        rvMovieList.apply {
            adapter = movieAdapter
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            addItemDecoration(ListDividerItemDecoration(context))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                        presenter.onScrollBeyond()
                    }
                }
            })
        }
        srlMovieList.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private val onMovieClicked = object : RecyclerViewHolder.Listener<MovieViewModel> {
        override fun onClick(viewModel: MovieViewModel) {
            val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ID_EXTRA, viewModel.id)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_by, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_popular_movies, R.id.action_upcoming_movies, R.id.action_top_rated_movies -> {
                presenter.changeListType(item.itemId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setItems(itens: List<MovieViewModel>, resetList: Boolean) {
        if (resetList) {
            movieAdapter.setItems(itens)
        } else {
            movieAdapter.addItems(itens)
        }
    }

    override fun startLoading() {
        srlMovieList.isRefreshing = true
    }

    override fun stopLoading() {
        srlMovieList.isRefreshing = false
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showNetworkError(message: Int) {
        Snackbar.make(rvMovieList, getString(message), Snackbar.LENGTH_LONG).apply {
            setAction(R.string.try_again){ presenter.tryAgain()}
        }.show()
    }

    override fun showGenericError(message: String) {
        Snackbar.make(rvMovieList, message, Snackbar.LENGTH_LONG).show()
    }
}
