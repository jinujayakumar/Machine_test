package com.example.kolomachinetest.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kolomachinetest.api.ApiCallback
import com.example.kolomachinetest.api.AppDataManager
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.repo.marvel.ErrorType
import com.example.kolomachinetest.api.repo.marvel.ListType
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Result
import retrofit2.Call
import java.util.ArrayList

open class BaseViewModel : ViewModel(), CallBack<ApiResponse> {

    val mApiResultLiveData = MutableLiveData<ResponseModel>()
    val mErrorLiveModel = MutableLiveData<ErrorModel>()
    private var mResponseModel = ResponseModel(arrayListOf(), true, 0, 0)

    fun fetchData(api: Call<ApiResponse>, position: Int, mListType: Int) {
        var refreshList = false
        if (position == 0) {
            if (mListType == ListType.TYPE_COMICS) {
                val result = AppDataManager.mComicsList
                if (result.isNotEmpty()) {
                    refreshList = true
                    saveAndUpdateData(result, true)
                }
            } else if (mListType == ListType.TYPE_CHARACTERS) {
                val result = AppDataManager.mCharacterList
                if (result.isNotEmpty()) {
                    refreshList = true
                    saveAndUpdateData(result, true)
                }
            }
        }
        mResponseModel.postion = position
        mResponseModel.mListType = mListType
        if (!refreshList) {
            api.enqueue(ApiCallback(this, position))
        }
    }

    override fun onSuccess(result: ApiResponse) {
        val results = result.data.results
        val showLoadingScreen = result.data.total != result.data.count + result.data.offset
        saveAndUpdateData(results, showLoadingScreen)
        if (result.data.total == 0 && mResponseModel.postion == 0) {
            mErrorLiveModel.value = ErrorModel("No data found", 0, ErrorType.TYPE_EMPTY)
        }
    }

    private fun saveAndUpdateData(
        results: ArrayList<Result>,
        showLoadingScreen: Boolean
    ) {
        mResponseModel.mList.clear()
        mResponseModel.mList.addAll(results)
        mResponseModel.mShowProgress = showLoadingScreen
        if (mResponseModel.mListType == ListType.TYPE_CHARACTERS) {
            AppDataManager.mCharacterList.addAll(results)
        } else if (mResponseModel.mListType == ListType.TYPE_COMICS) {
            AppDataManager.mComicsList.addAll(results)
        }
        mApiResultLiveData.value = mResponseModel
    }

    override fun onFailure(message: String?, pos: Int) {
        mErrorLiveModel.value = ErrorModel(message, pos)
    }
}