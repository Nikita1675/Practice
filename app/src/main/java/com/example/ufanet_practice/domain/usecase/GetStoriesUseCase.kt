package com.example.ufanet_practice.domain.usecase

import com.example.ufanet_practice.data.repository.StoriesRepository
import com.example.ufanet_practice.domain.model.Story
import com.example.ufanet_practice.domain.model.mapToDomain

class GetStoriesUseCase(private val repository: StoriesRepository) {
    suspend operator fun invoke(): List<Story> {
        val response = repository.getStories() // Получаем список StoryDto из репозитория
        return response.map { it.mapToDomain() } // Преобразуем StoryDto в Story
    }
}
