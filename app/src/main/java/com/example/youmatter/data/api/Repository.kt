package com.example.youmatter.data.api

import com.example.youmatter.data.model.ApiResponse
import com.example.youmatter.data.model.Login.LoginRequest
import com.example.youmatter.data.model.Login.LoginResponseSuccess
import com.example.youmatter.data.model.Register.RegisterRequest
import com.google.gson.JsonObject

class Repository(private val apiService: ApiService) {

    suspend fun loginRequest(loginData: LoginRequest): ApiResponse<LoginResponseSuccess>{
    return try {
        val response = apiService.login(loginData)
        if (response.isSuccessful) {
            response.body()?.let {
                ApiResponse.Success(it)
            } ?: ApiResponse.Failure("Response body is null")
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            ApiResponse.Failure(errorMessage)
        }
    } catch (e: Exception) {
        ApiResponse.Failure(e.message ?: "An error occurred")
    }
    }

    suspend fun registerRequest(registerData: RegisterRequest): ApiResponse<JsonObject>{
        return try {
            val response = apiService.register(registerData)
            if (response.isSuccessful){
                response.body()?.let{
                    ApiResponse.Success(it)
                } ?: ApiResponse.Failure("Response body is null")
            }else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                ApiResponse.Failure(errorMessage)
            }
        } catch (e: Exception) {
            ApiResponse.Failure(e.message ?: "An error occurred")
        }

    }
}