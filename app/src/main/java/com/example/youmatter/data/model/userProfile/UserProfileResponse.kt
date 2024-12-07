package com.example.youmatter.data.model.userProfile

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("data")
    var data: Data? = Data(),
    @SerializedName("message")
    var message : String? = null
)
