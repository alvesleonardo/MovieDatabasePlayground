package com.leonardoalves.testmoviedatabase.view.movieList

import android.view.View
import com.bumptech.glide.Glide
import com.leonardoalves.testmoviedatabase.R
import com.leonardoalves.feature_common.custom.ViewHolder

const val MOVIE_LIST_LAYOUT_ID = R.layout.list_item_movie

class MovieViewHolder(itemView: View, private val listener: Listener<MovieViewModel>) : ViewHolder<MovieViewModel>(itemView) {
    override fun bind(viewModel: MovieViewModel) {
        with(itemView){
            Glide.with(this)
                .load(viewModel.picture)
                .into(ivCover)
            tvTitle.text = viewModel.title
            tvDescription.text = viewModel.description
            setOnClickListener {
                listener.onClick(viewModel)
            }
        }
    }

    override fun recycle() {
        Glide.with(itemView)
            .clear(itemView.ivCover)
    }
}