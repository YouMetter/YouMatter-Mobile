package com.example.youmatter.data.model.testDepresi

import com.google.gson.annotations.SerializedName

data class Options(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("option")
    var option: String? = null,
)
