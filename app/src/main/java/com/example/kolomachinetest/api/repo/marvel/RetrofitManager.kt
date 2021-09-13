package com.example.kolomachinetest.api.repo.marvel

import com.example.kolomachinetest.uils.Utils
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitManager {

    private const val PUBLIC_API_KEY = "c891814ea12247a397de24d25db52d2e"
    private const val PRIVATE_API_KEY = "85734dd3c68644ac35ebc96e68c1cbd3744bbecb"
    private val mHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var mRetrofit: Retrofit
    var mService: MarvelApis

    init {
        mHttpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()
            val time = Date().time
            val ts = time.toString()
            val digest = Utils.getMD5Hash("${time}${PRIVATE_API_KEY}${PUBLIC_API_KEY}")
            val key = PUBLIC_API_KEY
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", key)
                .addQueryParameter("hash", digest)
                .build()

            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient.build())
            .build()
        mService = mRetrofit.create(MarvelApis::class.java)
    }
}