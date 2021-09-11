package com.example.kolomachinetest.api

import com.example.kolomachinetest.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApis {

    @GET("/v1/public/characters")
    fun getCharacterList(
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Call<ApiResponse>

}