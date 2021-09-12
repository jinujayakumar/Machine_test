package com.example.kolomachinetest.api


interface PaginationCallback {
    fun onLoadMore(position: Int)
}