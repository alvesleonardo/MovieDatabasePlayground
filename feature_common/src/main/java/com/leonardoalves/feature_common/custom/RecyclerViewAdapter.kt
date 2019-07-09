package com.leonardoalves.feature_common.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface RecyclerViewModel

interface ViewHolderFactory {
    fun getType(recyclerViewModel: RecyclerViewModel): Int
    fun getHolder(viewType: Int, view: View): RecyclerViewHolder<*>
}

abstract class RecyclerViewHolder<in T : RecyclerViewModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface Listener<T> {
        fun onClick(viewModel: T)
    }

    abstract fun bind(viewModel: T)
    abstract fun recycle()
}

class RecyclerViewAdapter(private val viewHolderFactory: ViewHolderFactory) : RecyclerView.Adapter<RecyclerViewHolder<RecyclerViewModel>>() {

    private var recyclerView: RecyclerView? = null

    private val items = arrayListOf<RecyclerViewModel>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<RecyclerViewModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return viewHolderFactory.getHolder(viewType, view) as RecyclerViewHolder<RecyclerViewModel>
    }

    override fun onBindViewHolder(holderRecycler: RecyclerViewHolder<RecyclerViewModel>, position: Int) {
        holderRecycler.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = viewHolderFactory.getType(items[position])

    fun setItems(items: List<RecyclerViewModel>) {
        with(this@RecyclerViewAdapter.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun addItems(items: List<RecyclerViewModel>) {
        val firstInsertionPosition = this@RecyclerViewAdapter.items.size + 1
        this@RecyclerViewAdapter.items.addAll(items)
        notifyItemRangeInserted(firstInsertionPosition, this@RecyclerViewAdapter.items.size)
    }

    override fun onViewRecycled(holderRecycler: RecyclerViewHolder<RecyclerViewModel>) {
        holderRecycler.recycle()
    }

    fun getItemByPosition(position: Int) = items[position]

    fun getItem(recyclerViewModel: RecyclerViewModel): RecyclerViewModel? {
        val index = items.indexOf(recyclerViewModel)
        if (index < 0 )return null
        return items[index]
    }

    fun <R : RecyclerViewModel> getItemFromType(kClass: Class<R>): List<R> {
        return items.filterIsInstance(kClass)
    }

}