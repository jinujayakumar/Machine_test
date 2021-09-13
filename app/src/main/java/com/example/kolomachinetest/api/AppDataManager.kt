package com.example.kolomachinetest.api

import android.content.Context
import androidx.room.Room
import com.example.kolomachinetest.api.repo.local.AppDatabase
import com.example.kolomachinetest.api.repo.local.Search
import com.example.kolomachinetest.api.repo.marvel.RetrofitManager
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse
import retrofit2.Call


object AppDataManager {

    private var db: AppDatabase? = null

    fun fetchCharacterList(index: Int): Call<ApiResponse> {
        return RetrofitManager.mService.fetchCharacterList("50", index.toString())
    }

    fun fetchComicsList(index: Int): Call<ApiResponse> {
        return RetrofitManager.mService.fetchComics("50", index.toString())
    }

    fun initDb(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "search-db"
        ).allowMainThreadQueries().build()
    }

    fun searchList(): List<Search>? {
        return db?.userDao()?.getSearchList()
    }

    fun searchComicsList(
        format: String, formatType: String,
        noVariants: String, dateDescriptor: String, index: Int
    ): Call<ApiResponse> {
        return RetrofitManager.mService.filterComics(
            format, formatType, noVariants, dateDescriptor,
            "50", index.toString()
        )
    }

    fun searchCharacterList(index: Int, name: String): Call<ApiResponse> {
        return RetrofitManager.mService.searchCharacterList(name, "50", index.toString())
    }

    fun insertSearch(key: String) {
        val search = Search(key.hashCode(), key)
        db?.userDao()?.insertSearchQuery(search)
    }
}