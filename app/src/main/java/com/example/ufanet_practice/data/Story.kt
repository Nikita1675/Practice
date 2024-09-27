package com.example.ufanet_practice.data

data class Story(
    val newsName: String,
    val imageLogo: String,
    val url: String,
    var isFavorite: Boolean = false
)

data class StoriesResponse(
    val stories: List<Story>
)
