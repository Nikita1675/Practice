package com.example.ufanet_practice.domain.usecase

import com.example.ufanet_practice.data.repository.StoriesRepository
import com.example.ufanet_practice.data.model.StoryDto
import com.example.ufanet_practice.domain.model.Story

class GetStoriesUseCase(private val repository: StoriesRepository) {
    suspend operator fun invoke(): List<Story> {
        val response: List<StoryDto> = repository.getStories()
        return response.map { storyDto ->
            Story(
                newsName = storyDto.newsName,
                imageLogo = storyDto.imageLogo,
                url = storyDto.url,
                isFavorite = storyDto.isFavorite,
                uniqueName = storyDto.uniqueName
            )
        }
    }
}
