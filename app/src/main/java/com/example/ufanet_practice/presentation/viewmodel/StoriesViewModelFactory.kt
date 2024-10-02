package com.example.ufanet_practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase

class StoriesViewModelFactory(
    private val getStoriesUseCase: GetStoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoriesViewModel::class.java)) {
            return StoriesViewModel(getStoriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
