package com.example.kolomachinetest.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.uils.Utils
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object RemoteApiBuilder {

    internal const val PUBLIC_API_KEY = "c891814ea12247a397de24d25db52d2e"
    internal const val PRIVATE_API_KEY = "85734dd3c68644ac35ebc96e68c1cbd3744bbecb"
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit
    internal var service: MarvelApis

    init {
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        }
        retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        service = retrofit.create(MarvelApis::class.java)
    }

    fun getCharacterList(callback: CallBack<ApiResponse>) {
        val time = Date().time
        val ts = time.toString()
        val digest = Utils.getMD5Hash("${time}${PRIVATE_API_KEY}${PUBLIC_API_KEY}")
        val key = PUBLIC_API_KEY
        service.getCharacterList(ts, key, digest, "20", "0")
            .enqueue(ApiCallback(callback))

    }

    fun getCharacterList(index: Int, callback: CallBack<ApiResponse>) {
        val time = Date().time
        val ts = time.toString()
        val digest = Utils.getMD5Hash("${time}${PRIVATE_API_KEY}${PUBLIC_API_KEY}")
        val key = PUBLIC_API_KEY
        return service.getCharacterList(ts, key, digest, "20", index.toString())
            .enqueue(ApiCallback(callback))
    }
}