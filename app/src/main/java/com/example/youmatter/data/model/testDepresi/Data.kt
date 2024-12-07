package com.example.youmatter.data.model.testDepresi

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("social")
    var social: Social = Social(),
    @SerializedName("workingworld")
    var workingworld: WorkingWorld = WorkingWorld(),
    @SerializedName("studying")
    var studying: Studying = Studying()
)
