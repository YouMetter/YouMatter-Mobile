package com.example.youmatter.data.api

import com.example.youmatter.data.model.userProfile.UserProfileResponse
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Headers

interface ApiService {
    @GET("user/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<UserProfileResponse>
}