package com.example.youmatter.data.model.Register

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null
)

