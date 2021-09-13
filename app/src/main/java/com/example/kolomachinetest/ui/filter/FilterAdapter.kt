package com.example.kolomachinetest.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kolomachinetest.R
import com.example.kolomachinetest.ui.adapter.PaginationCallback
import com.example.kolomachinetest.api.repo.marvel.data.Result
import com.example.kolomachinetest.ui.adapter.PaginationAdapter
import com.example.kolomachinetest.uils.Utils

class FilterAdapter(
    list: MutableList<Result>, callback: PaginationCallback,
    private val isSearch: Boolean
) :
    PaginationAdapter<FilterAdapter.FilterViewHolder, Result>(list, callback) {

    class FilterViewHolder(view: View) : PaginationViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, pos: Int, model: Result) {
        if (!isSearch) {
            holder.textView.text = model.name
        } else {
            holder.textView.text = model.title
        }
        val url = "${model.thumbnail.path}.${model.thumbnail.extension}"
        Glide.with(holder.textView.context)
            .load(url)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .listener(Utils.loadPallet(holder.itemView, holder.imageView.context))
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comics_list, parent, false)
        return FilterViewHolder(view)
    }
}