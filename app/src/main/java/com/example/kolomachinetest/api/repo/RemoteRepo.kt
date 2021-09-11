package com.example.kolomachinetest.api.repo

import com.example.kolomachinetest.api.ApiCallback
import com.example.kolomachinetest.api.CallBack
import com.example.kolomachinetest.api.RemoteApiBuilder
import com.example.kolomachinetest.data.ApiResponse
import com.example.kolomachinetest.uils.Utils
import java.util.*

class RemoteRepo : MarvelRepo {

    override fun getCharacterList(size: String, limit: String, callback: CallBack<ApiResponse>) {
        val time = Date().time
        val ts = time.toString()
        val digest =
            Utils.getMD5Hash("${time}${RemoteApiBuilder.PRIVATE_API_KEY}${RemoteApiBuilder.PUBLIC_API_KEY}")
        val key = RemoteApiBuilder.PUBLIC_API_KEY
        RemoteApiBuilder.service.getCharacterList(ts, key, digest, "20", size)
            .enqueue(ApiCallback(callback))
    }
}