package com.example.ufanet_practice.data.repository

import com.example.ufanet_practice.data.api.RetrofitInstance
import com.example.ufanet_practice.data.model.StoryDto

class StoriesRepository {
    suspend fun getStories(): List<StoryDto> {
        val response = RetrofitInstance.api.getStories()
        return if (response.isSuccessful) {
            response.body()?.detail?.stories ?: emptyList()
        } else {
            emptyList()
        }
    }
}
