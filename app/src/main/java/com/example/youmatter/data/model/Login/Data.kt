package com.example.youmatter.data.model.Login

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("token")
    var token: String? = null,
)
