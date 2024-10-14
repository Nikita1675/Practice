package com.example.ufanet_practice.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase

class FavoritesStoryViewModelFactory(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesStoryViewModel::class.java)) {
            return FavoritesStoryViewModel(application, getStoriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
