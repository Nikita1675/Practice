package com.example.ufanet_practice.di

import com.example.ufanet_practice.data.api.ApiService
import com.example.ufanet_practice.data.api.RetrofitInstance
import com.example.ufanet_practice.data.repository.StoriesRepository
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideStoriesRepository(apiService: ApiService): StoriesRepository {
        return StoriesRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideGetStoriesUseCase(repository: StoriesRepository): GetStoriesUseCase {
        return GetStoriesUseCase(repository)
    }
}
