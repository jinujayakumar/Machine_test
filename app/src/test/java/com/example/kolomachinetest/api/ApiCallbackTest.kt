package com.example.kolomachinetest.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Call
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiCallbackTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mCall: Call<ApiResponse>

    @Mock
    private lateinit var mCallBack: CallBack<ApiResponse>

    @Mock
    private lateinit var mApiResponse: Response<ApiResponse>

    private lateinit var mApiCallback: ApiCallback<ApiResponse>

    @Mock
    private lateinit var mApiResponseModel: ApiResponse

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        mApiCallback = ApiCallback(mCallBack, 0)
    }

    @Test
    fun onResponse() {
        `when`(mApiResponse.isSuccessful).thenReturn(true)
        `when`(mApiResponse.body()).thenReturn(mApiResponseModel)
        mApiCallback.onResponse(mCall, mApiResponse)
        verify(mCallBack).onSuccess(mApiResponseModel)
    }

    @Test
    fun onFailure() {
        val error = mock(Throwable::class.java)
        `when`(error.message).thenReturn("Test")
        mApiCallback.onFailure(mCall, error)
        verify(mCallBack).onFailure("Test", 0)
    }
}