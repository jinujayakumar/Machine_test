package com.example.kolomachinetest.api

import android.content.Context
import androidx.room.Room
import com.example.kolomachinetest.api.repo.local.AppDatabase
import com.example.kolomachinetest.api.repo.local.Search
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.uils.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import okhttp3.HttpUrl

import okhttp3.Request


object RemoteApiBuilder {

    private const val PUBLIC_API_KEY = "c891814ea12247a397de24d25db52d2e"
    private const val PRIVATE_API_KEY = "85734dd3c68644ac35ebc96e68c1cbd3744bbecb"
    private val mHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private var mRetrofit: Retrofit
    private var mService: MarvelApis
    private var db: AppDatabase? = null

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

    fun fetchCharacterList(index: Int, callback: CallBack<ApiResponse>) {
        mService.fetchCharacterList("50", index.toString())
            .enqueue(ApiCallback(callback))
    }

    fun fetchComicsList(index: Int, callback: CallBack<ApiResponse>) {
        mService.fetchComics("50", index.toString())
            .enqueue(ApiCallback(callback))
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
        noVariants: String, dateDescriptor: String, index: Int,
        callback: CallBack<ApiResponse>) {
        mService.filterComics(format, formatType, noVariants, dateDescriptor,
            "50", index.toString())
            .enqueue(ApiCallback(callback))
    }

    fun searchCharacterList(index: Int, name: String, callback: CallBack<ApiResponse>) {
        mService.searchCharacterList(name, "50", index.toString())
            .enqueue(ApiCallback(callback))
    }

    fun insertSearch(key: String) {
        val search = Search(key.hashCode(), key)
        db?.userDao()?.insertSearchQuery(search)
    }
}