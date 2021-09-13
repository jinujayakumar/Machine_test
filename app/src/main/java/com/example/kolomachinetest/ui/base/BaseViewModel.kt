package com.example.kolomachinetest.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kolomachinetest.api.ApiCallback
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import retrofit2.Call

class BaseViewModel : ViewModel(), CallBack<ApiResponse> {

    val mApiResultLiveData = MutableLiveData<ResponseModel>()
    val mErrorLiveModel = MutableLiveData<ErrorModel>()
    private val text = ResponseModel(arrayListOf(), true)

    fun fetchData(api: Call<ApiResponse>, position: Int) {
        api.enqueue(ApiCallback(this, position))
    }

    override fun onSuccess(result: ApiResponse) {
        val results = result.data.results
        val showLoadingScreen = result.data.total != result.data.count + result.data.offset
        text.list.addAll(results)
        text.showProgress = showLoadingScreen
        mApiResultLiveData.value = text
    }

    override fun onFailure(message: String?, pos: Int) {
        mErrorLiveModel.value = ErrorModel(message, pos)
    }
}