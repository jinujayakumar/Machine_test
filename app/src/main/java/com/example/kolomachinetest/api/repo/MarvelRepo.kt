package com.example.kolomachinetest.api.repo

import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.data.ApiResponse

interface MarvelRepo {
    fun getCharacterList(size: String, limit: String, callback: CallBack<ApiResponse>)
}