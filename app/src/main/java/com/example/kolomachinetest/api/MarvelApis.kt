package com.example.kolomachinetest.api

import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.data.Comics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MarvelApis {

    @GET("characters")
    fun fetchCharacterList(
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): Call<ApiResponse>

    @GET("characters")
    fun searchCharacterList(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): Call<ApiResponse>

    @GET("comics")
    fun fetchComics(
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): Call<ApiResponse>


    @GET("comics")
    fun filterComics(
        @Query("format") format: String,
        @Query("formatType") formatType: String,
        @Query("noVariants") noVariants: String,
        @Query("dateDescriptor") dateDescriptor: String,
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): Call<ApiResponse>

}