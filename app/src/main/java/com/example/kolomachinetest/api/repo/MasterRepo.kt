package com.example.kolomachinetest.api.repo

import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.data.ApiResponse

object MasterRepo : MarvelRepo {

    private val remoteRepo = RemoteRepo()

    override fun getCharacterList(size: String, limit: String, callback: CallBack<ApiResponse>) {
        remoteRepo.getCharacterList(size, limit, callback)
    }
}