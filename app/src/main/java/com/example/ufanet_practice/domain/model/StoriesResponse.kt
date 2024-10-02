package com.example.ufanet_practice.domain.model

data class StoriesResponse(
    val detail: Detail
)

data class Detail(
    val stories: List<Story>
)
