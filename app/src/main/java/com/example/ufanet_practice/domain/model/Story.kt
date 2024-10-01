package com.example.ufanet_practice.domain.model

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("news_name") val newsName: String?,
    @SerializedName("image_logo") val imageLogo: String?,
    @SerializedName("url") val url: String,
    var isFavorite: Boolean = false
)


data class StoriesResponse(
    val detail: Detail
)

data class Detail(
    val stories: List<Story>
)

