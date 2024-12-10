package com.example.youmatter.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    var code: Int? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("message")
    var message: String? = null
)
