package com.example.youmatter.data.model.testDepresi

import com.google.gson.annotations.SerializedName

data class Questions(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("question")
    var question: String? = null,
    @SerializedName("options")
    var options: ArrayList<Options>? = arrayListOf(),

)
