package com.example.ufanet_practice.presentation.model

data class StoryPresentation(
    val newsName: String?,
    val imageLogo: String?,
    val url: String?,
    val isFavorite: Boolean,
    var uniqueName: String?
)
