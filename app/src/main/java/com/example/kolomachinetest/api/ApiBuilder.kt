package com.example.kolomachinetest.api

import com.example.kolomachinetest.data.ApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


object ApiBuilder {

    private const val PUBLIC_API_KEY = "c891814ea12247a397de24d25db52d2e"
    private const val PRIVATE_API_KEY = "85734dd3c68644ac35ebc96e68c1cbd3744bbecb"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com:443")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: MarvelApis = retrofit.create(MarvelApis::class.java)

    fun getCharacterList(callback: ApiCallback.RestCallback<ApiResponse>) {
        val time = Date().time
        val ts = time.toString()
        val digest = md5("${time}${PRIVATE_API_KEY}${PUBLIC_API_KEY}")
        val key = PUBLIC_API_KEY
        service.getCharacterList(key, digest, ts)
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