package com.example.youmatter.data.model.testDepresi.Request

import com.google.gson.annotations.SerializedName

data class AnswerOption(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("answer")
    var answer: String? = null,
)
