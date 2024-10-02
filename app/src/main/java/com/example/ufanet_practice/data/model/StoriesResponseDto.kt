package com.example.ufanet_practice.data.model

data class StoriesResponseDto(
    val detail: DetailDto
)

data class DetailDto(
    val stories: List<StoryDto>
)
