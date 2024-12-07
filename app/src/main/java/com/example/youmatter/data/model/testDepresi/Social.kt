package com.example.youmatter.data.model.testDepresi

import com.google.gson.annotations.SerializedName

data class Social(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("questions")
    var questions: ArrayList<Questions>? = arrayListOf(),

)
