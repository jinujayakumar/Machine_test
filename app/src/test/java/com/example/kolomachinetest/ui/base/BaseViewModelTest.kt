package com.example.kolomachinetest.ui.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.kolomachinetest.FieldSetter
import com.example.kolomachinetest.api.AppDataManager
import com.example.kolomachinetest.api.repo.marvel.ListType
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import com.example.kolomachinetest.api.repo.marvel.data.Data
import com.example.kolomachinetest.api.repo.marvel.data.Result
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import retrofit2.Call
import java.util.*

@RunWith(JUnit4::class)
class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    private lateinit var model: BaseViewModel

    @Mock
    private lateinit var call: Call<ApiResponse>

    @Spy
    private lateinit var mApiResultLiveData: MutableLiveData<ResponseModel>

    @Spy
    private lateinit var mErrorLiveModel: MutableLiveData<ErrorModel>

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        FieldSetter.setField(
            model,
            "mApiResultLiveData",
            mApiResultLiveData
        )
        FieldSetter.setField(
            model,
            "mErrorLiveModel",
            mErrorLiveModel
        )
    }

    @Test
    fun fetchData() {
        val arrayList = ArrayList<Result>()
        arrayList.add(mock(Result::class.java))
        FieldSetter.setField(
            AppDataManager,
            "mCharacterList", arrayList
        )
        model.fetchData(call, 0, ListType.TYPE_CHARACTERS)
        assertEquals(model.mApiResultLiveData.value?.mList, arrayList)
    }

    @Test
    fun onSuccess() {
        val apiResponse = spy(ApiResponse::class.java)
        val data = spy(Data::class.java)
        val arrayList = ArrayList<Result>()
        arrayList.add(mock(Result::class.java))
        data.setResults(arrayList)
        apiResponse.setData(data)
        model.onSuccess(apiResponse)
        Assert.assertEquals(model.mApiResultLiveData.value?.mList, arrayList)
    }

    @Test
    fun onFailure() {
        val message = "Test"
        model.onFailure(message, 0)
        Assert.assertEquals(model.mErrorLiveModel.value?.message, message)
    }
}