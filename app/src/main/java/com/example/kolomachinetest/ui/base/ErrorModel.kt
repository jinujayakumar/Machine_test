package com.example.kolomachinetest.ui.base

import com.example.kolomachinetest.api.repo.marvel.ErrorType

data class ErrorModel(
    var message: String? = null,
    var position: Int = 0,
    var errorType: Int = ErrorType.TYPE_UNKNOWN
)