package com.example.kolomachinetest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.ui.adapter.PaginationAdapter.PaginationViewHolder
import java.util.*

abstract class PaginationAdapter<T : PaginationViewHolder?, M>(
    private val mListModel: MutableList<M>,
    private val mPaginationCallback: PaginationCallback
) : RecyclerView.Adapter<T>() {

    private var mLastItemPost = 0
    private var mShowLoadingScreen = true

    companion object {
        private const val LIST_ITEM = 1
        private const val LOADING_ITEM = 0
    }

    abstract fun onBindViewHolder(holder: T, pos: Int, model: M)

    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): T

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return if (viewType == LIST_ITEM) {
            onCreateItemViewHolder(parent, viewType)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_item, parent, false)
            LoadingViewHolder(view) as T
        }
    }

    final override fun onBindViewHolder(holder: T, position: Int) {
        if (holder !is LoadingViewHolder) {
            onBindViewHolder(holder, position, mListModel[position])
        } else {
            checkLastPosition(position)
        }
    }

    private fun checkLastPosition(position: Int) {
        if (position != mLastItemPost) {
            mPaginationCallback.onLoadMore(position)
            mLastItemPost = position
        }
    }

    final override fun getItemCount(): Int {
        return if (mShowLoadingScreen && mListModel.isNotEmpty()) {
            mListModel.size + 1
        } else {
            mListModel.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mListModel.size && mListModel.isNotEmpty()) {
            LOADING_ITEM
        } else {
            LIST_ITEM
        }
    }

    fun resetLastItem() {
        mLastItemPost = 0
    }

    fun setResult(results: ArrayList<M>, showLoadingScreen: Boolean) {
        mShowLoadingScreen = showLoadingScreen
        val size = results.size
        val oldSize = mListModel.size
        mListModel.addAll(results)
        if (showLoadingScreen) {
            notifyItemRangeInserted(oldSize, size)
        } else {
            notifyItemRangeInserted(oldSize, size - 1)
        }
    }

    open class PaginationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : PaginationViewHolder(itemView)
}