package com.example.kolomachinetest.api

interface CallBack<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String?, pos: Int)
}