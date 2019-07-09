package com.leonardoalves.testmoviedatabase.view.movieList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leonardoalves.testmoviedatabase.R
import com.leonardoalves.feature_common.custom.*
import com.leonardoalves.testmoviedatabase.view.movieDetail.MovieDetailActivity
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
            override fun getType(viewModel: ViewModel): Int = when (viewModel) {
                is MovieViewModel -> MOVIE_LIST_LAYOUT_ID
                else -> throw IllegalArgumentException()
            }

            override fun getHolder(viewType: Int, view: View) = when (viewType) {
                MOVIE_LIST_LAYOUT_ID -> MovieViewHolder(view, onMovieClicked)
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
                    if (linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2){
                        presenter.onScrollBeyond()
                    }
                }
            })
        }
        srlMovieList.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private val onMovieClicked = object : ViewHolder.Listener<MovieViewModel>{
        override fun onClick(viewModel: MovieViewModel) {
            val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_sort_by, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_popular_movies -> {presenter.changeListType(MovieListPresenter.Type.POPULAR);true}
            R.id.action_upcoming_movies -> {presenter.changeListType(MovieListPresenter.Type.UPCOMING);true}
            R.id.action_top_rated_movies -> {presenter.changeListType(MovieListPresenter.Type.TOP_RATED);true}
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setItems(itens: List<MovieViewModel>, resetList: Boolean) {
        if (resetList){
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
}
