package com.example.kolomachinetest.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallback<T>(private val mCallback: CallBack<T>) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                mCallback.onSuccess(result)
            }
        } else {
            mCallback.onFailure(response.message())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        mCallback.onFailure(t.message)
    }
}