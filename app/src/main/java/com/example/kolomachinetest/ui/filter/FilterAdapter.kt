package com.example.kolomachinetest.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kolomachinetest.R
import com.example.kolomachinetest.api.PaginationCallback
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.ui.adapter.PaginationAdapter

class FilterAdapter(list: MutableList<ApiResponse>, callback: PaginationCallback) :
    PaginationAdapter<FilterAdapter.FilterViewHolder, ApiResponse>(list, callback) {

    class FilterViewHolder(view: View) : PaginationViewHolder(view) {

    }

    override fun onBindViewHolder(holder: FilterViewHolder, pos: Int, model: ApiResponse) {

    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comics_list, parent, false)
        return FilterViewHolder(view)
    }
}