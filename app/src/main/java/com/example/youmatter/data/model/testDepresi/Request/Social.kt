package com.example.youmatter.data.model.testDepresi.Request

import com.google.gson.annotations.SerializedName

data class Social(
    @SerializedName("answerOption")
    var answerOption: ArrayList<AnswerOption> = arrayListOf()
    )
