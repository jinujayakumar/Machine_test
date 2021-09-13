package com.example.kolomachinetest.ui.base

import com.example.kolomachinetest.api.repo.marvel.data.Result

data class ResponseModel(
    val list: ArrayList<Result>,
    var showProgress: Boolean
)