package com.example.youmatter.data.model.testDepresi

import com.google.gson.annotations.SerializedName

data class TestDepresiResponse(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("data")
    var data: Data? = Data(),
    @SerializedName("message")
    var message : String? = null
)
