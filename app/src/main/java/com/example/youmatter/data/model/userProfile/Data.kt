package com.example.youmatter.data.model.userProfile

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("profileImage")
    var profileImage: String? = null,
    @SerializedName("email")
    var email: String? = null
)
