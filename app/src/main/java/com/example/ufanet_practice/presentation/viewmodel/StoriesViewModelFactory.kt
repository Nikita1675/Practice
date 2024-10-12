package com.example.ufanet_practice.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase

class StoriesFavoritesViewModelFactory(
    private val application: Application,
    private val getStoriesUseCase: GetStoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoriesFavoritesViewModel::class.java)) {
            return StoriesFavoritesViewModel(application, getStoriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
