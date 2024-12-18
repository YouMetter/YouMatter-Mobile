package com.example.youmatter.data.model

sealed class ApiResponse<out T>{
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Failure(val error: String) : ApiResponse<Nothing>()
}
