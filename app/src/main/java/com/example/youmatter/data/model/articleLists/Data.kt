package com.example.youmatter.data.model.articleLists

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("coverImage")
    val coverImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null
)
