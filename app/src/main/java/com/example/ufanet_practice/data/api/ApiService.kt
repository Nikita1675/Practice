package com.example.ufanet_practice.data.api

import com.example.ufanet_practice.data.model.StoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v0/stories")
    suspend fun getStories(): Response<StoriesResponse>
}
