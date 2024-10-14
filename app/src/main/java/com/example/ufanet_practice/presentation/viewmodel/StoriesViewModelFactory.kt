package com.example.ufanet_practice.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.R // Не забудьте импортировать R

class StoriesViewModelFactory(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val context: Context // Добавление контекста для доступа к строковым ресурсам
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoriesViewModel::class.java)) {
            return StoriesViewModel(getStoriesUseCase) as T
        }
        throw IllegalArgumentException(context.getString(R.string.unknown_view_model_class)) // Использование строкового ресурса
    }
}
