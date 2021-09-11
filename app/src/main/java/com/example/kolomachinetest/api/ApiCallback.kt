package com.example.kolomachinetest.api

import com.example.kolomachinetest.api.ApiCallback.RestCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallback<T>(private val mCallback: RestCallback<T?>) : Callback<T> {
    interface RestCallback<T> {
        fun onSuccess(result: T)
        fun onFailure(message: String?)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            mCallback.onSuccess(response.body())
        } else {
            mCallback.onFailure(response.message())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        mCallback.onFailure(t.message)
    }
}