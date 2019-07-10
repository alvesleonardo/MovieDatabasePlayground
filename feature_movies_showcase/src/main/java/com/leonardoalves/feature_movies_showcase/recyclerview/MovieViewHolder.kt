package com.leonardoalves.feature_movies_showcase.recyclerview

import android.view.View
import com.bumptech.glide.Glide
import com.leonardoalves.feature_common.custom.RecyclerViewHolder
import com.leonardoalves.feature_movies_showcase.R
import kotlinx.android.synthetic.main.list_item_movie.view.*
class MovieViewHolder(itemView: View, private val listener: Listener<MovieViewModel>) : RecyclerViewHolder<MovieViewModel>(itemView) {

    companion object{
        var MOVIE_LIST_LAYOUT_ID = R.layout.list_item_movie
    }
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