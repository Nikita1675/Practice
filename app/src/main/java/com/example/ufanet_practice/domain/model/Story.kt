package com.example.ufanet_practice.domain.model

data class Story(
    val newsName: String?,
    val imageLogo: String?,
    val url: String?,
    val isFavorite: Boolean ,
    var uniqueName: String?
)
