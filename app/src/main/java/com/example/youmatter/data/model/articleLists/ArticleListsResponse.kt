package com.example.youmatter.data.model.articleLists

import com.google.gson.annotations.SerializedName

data class ArticleListsResponse(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("data")
    var data: ArrayList<Data>? = arrayListOf(),
    @SerializedName("message")
    var message : String? = null
)
