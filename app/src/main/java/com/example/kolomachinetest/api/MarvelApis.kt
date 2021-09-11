package com.example.kolomachinetest.api

import com.example.kolomachinetest.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApis {

    @GET("characters")
    fun getCharacterList(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): Call<ApiResponse>

}