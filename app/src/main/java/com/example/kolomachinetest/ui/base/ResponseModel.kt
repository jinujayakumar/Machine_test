package com.example.kolomachinetest.ui.base

import com.example.kolomachinetest.api.repo.marvel.data.Result

data class ResponseModel(
    val mList: ArrayList<Result>,
    var mShowProgress: Boolean,
    var mListType: Int,
    var postion: Int,
)