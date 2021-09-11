package com.example.kolomachinetest.api

import com.example.kolomachinetest.data.ApiResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


object ApiBuilder {

    private const val PUBLIC_API_KEY = "c891814ea12247a397de24d25db52d2e"
    private const val PRIVATE_API_KEY = "85734dd3c68644ac35ebc96e68c1cbd3744bbecb"
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit
    private var service: MarvelApis

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

    fun getCharacterList(callback: ApiCallback.RestCallback<ApiResponse>) {
        val time = "thesoer"
        val ts = time
        val digest = md5("${time}${PRIVATE_API_KEY}${PUBLIC_API_KEY}")
        val key = PUBLIC_API_KEY
        service.getCharacterList(ts, key, digest)
            .enqueue(ApiCallback(callback))

    }

    fun md5(s: String): String {
        val md5 = "MD5"
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest
                .getInstance(md5)
            digest.update(s.toByteArray())
            val messageDigest: ByteArray = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

}