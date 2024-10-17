package com.example.ufanet_practice.data.repository

import com.example.ufanet_practice.data.api.ApiService
import com.example.ufanet_practice.data.model.StoryDto

class StoriesRepository(private val apiService: ApiService) {
    suspend fun getStories(): List<StoryDto> {
        val response = apiService.getStories()
        return if (response.isSuccessful) {
            response.body()?.detail?.stories ?: emptyList()
        } else {
            emptyList()
        }
    }
}
