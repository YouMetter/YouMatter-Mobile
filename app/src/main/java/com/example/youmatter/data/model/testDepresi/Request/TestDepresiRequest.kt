package com.example.youmatter.data.model.testDepresi.Request

import com.google.gson.annotations.SerializedName

data class TestDepresiRequest(
    @SerializedName("social")
    var social: Social = Social(),
    @SerializedName("workingworld")
    var workingworld: WorkingWorld = WorkingWorld(),
    @SerializedName("studying")
    var studying: Studying = Studying(),
)
