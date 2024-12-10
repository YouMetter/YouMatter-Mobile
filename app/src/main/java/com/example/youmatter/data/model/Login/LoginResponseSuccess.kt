package com.example.youmatter.data.model.Login

import com.google.gson.annotations.SerializedName

data class LoginResponseSuccess(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("data")
    var data: Data = Data(),
    @SerializedName("message")
    var message : String? = null
)
