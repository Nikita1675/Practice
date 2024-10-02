package com.example.ufanet_practice.domain.usecase

import com.example.ufanet_practice.data.repository.StoriesRepository
import com.example.ufanet_practice.domain.model.Story

class GetStoriesUseCase(private val repository: StoriesRepository) {
    suspend operator fun invoke(): List<Story> {
        val response = repository.getStories()
        return response?.detail?.stories?.map { storyDto ->
            Story(
                newsName = storyDto.newsName,
                imageLogo = storyDto.imageLogo,
                url = storyDto.url,
                isFavorite = storyDto.isFavorite // Обязательно проверьте наличие этого свойства в StoryDto
            )
        } ?: emptyList()
    }
}
