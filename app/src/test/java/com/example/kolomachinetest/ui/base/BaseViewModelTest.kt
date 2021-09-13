package com.example.kolomachinetest.ui.base

import com.example.kolomachinetest.api.AppDataManager
import com.example.kolomachinetest.api.repo.marvel.ListType
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Data
import com.example.kolomachinetest.api.repo.marvel.data.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.internal.util.reflection.FieldSetter
import retrofit2.Call
import java.util.*

class BaseViewModelTest {

    private lateinit var model: BaseViewModel

    @Mock
    private lateinit var call: Call<ApiResponse>

    private lateinit var mApiResultLiveData: ResponseModel

    private lateinit var mErrorLiveModel: ErrorModel

    @Before
    fun before() {
        model = mock(BaseViewModel::class.java)
        MockitoAnnotations.initMocks(this)
        mApiResultLiveData = mock(ResponseModel::class.java)
        mErrorLiveModel = mock(ErrorModel::class.java)
        FieldSetter.setField(
            model,
            BaseViewModel::class.java.getDeclaredField("mApiResultLiveData"),
            mApiResultLiveData
        )
        FieldSetter.setField(
            model,
            BaseViewModel::class.java.getDeclaredField("mErrorLiveModel"),
            mErrorLiveModel
        )
    }


    @Test
    fun fetchData() {
        model.fetchData(call, 0, ListType.TYPE_CHARACTERS)
        val arrayList = ArrayList<Result>()
        arrayList.add(mock(Result::class.java))
        FieldSetter.setField(
            AppDataManager,
            AppDataManager::class.java.getDeclaredField("mCharacterList"), arrayList
        )
        verify(model.mApiResultLiveData).value
    }

    @Test
    fun onSuccess() {
        val apiResponse = mock(ApiResponse::class.java)
        val data = mock(Data::class.java)
        val arrayList = ArrayList<Result>()
        arrayList.add(mock(Result::class.java))
        val result = Mockito.`when`(data.results).thenReturn(arrayList)
        Mockito.`when`(apiResponse.data).thenReturn(data)
        model.onSuccess(apiResponse)
        Assert.assertEquals(model.mApiResultLiveData.value?.mList, result)
    }

    @Test
    fun onFailure() {
        val message = "Test"
        model.onFailure(message, 0)
        Assert.assertEquals(model.mErrorLiveModel.value?.message, message)
    }
}