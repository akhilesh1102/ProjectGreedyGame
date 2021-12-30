package com.antinolabs.projectgreedygame.utils

import com.antinolabs.projectgreedygame.model.PopularMovie

sealed class ApiResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? =null) : ApiResponse<T>(data)
    class Error<T>(message: String?, data: T? = null) :
        ApiResponse<T>(data, message)
}

