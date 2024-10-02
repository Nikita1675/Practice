package com.example.ufanet_practice.data.repository

import com.example.ufanet_practice.data.api.RetrofitInstance
import com.example.ufanet_practice.data.model.StoriesResponseDto

class StoriesRepository {
    suspend fun getStories(): StoriesResponseDto? {
        val response = RetrofitInstance.api.getStories()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
