package com.example.youmatter.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponseError(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("error")
    var error: Error = Error(),
)
