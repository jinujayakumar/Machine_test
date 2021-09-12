package com.example.kolomachinetest.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Result
import com.example.kolomachinetest.ui.adapter.PaginationAdapter

class FilterAdapter(list: MutableList<Result>, callback: PaginationCallback) :
    PaginationAdapter<FilterAdapter.FilterViewHolder, Result>(list, callback) {

    class FilterViewHolder(view: View) : PaginationViewHolder(view) {
        val imageView : ImageView = view.findViewById<ImageView>(R.id.imageView)
        val textView : TextView = view.findViewById<TextView>(R.id.textView)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, pos: Int, model: Result) {
        holder.textView.text = model.title
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comics_list, parent, false)
        return FilterViewHolder(view)
    }
}