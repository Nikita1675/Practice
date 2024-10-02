package com.example.ufanet_practice.data.model

import com.google.gson.annotations.SerializedName

data class StoryDto(
    @SerializedName("news_name") val newsName: String?,
    @SerializedName("image_logo") val imageLogo: String?,
    @SerializedName("url") val url: String,
    @SerializedName("is_favorite") val isFavorite: Boolean
)
