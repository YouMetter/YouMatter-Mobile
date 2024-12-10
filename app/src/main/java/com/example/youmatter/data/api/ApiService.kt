package com.example.youmatter.data.api

import com.example.youmatter.data.model.Login.LoginRequest
import com.example.youmatter.data.model.Login.LoginResponseSuccess
import com.example.youmatter.data.model.Register.RegisterRequest
import com.example.youmatter.data.model.testDepresi.Request.TestDepresiRequest
import com.example.youmatter.data.model.userProfile.UserProfileResponse
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("user/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<UserProfileResponse>

    @POST("test/depresi")
    suspend fun postDepresi(
        @Body request: TestDepresiRequest
    ): Response<JsonArray>

    @POST("user/register")
     suspend fun register(
        @Body request: RegisterRequest
    ): Response<JsonObject>

    @POST("user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponseSuccess>
}