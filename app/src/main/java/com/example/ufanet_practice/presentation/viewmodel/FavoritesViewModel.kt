package com.example.ufanet_practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.ufanet_practice.domain.model.Story

class FavoritesViewModel : ViewModel() {
    // Массив для хранения избранных историй
    private val _favoriteStories = mutableStateListOf<Story>()
    val favoriteStories: List<Story> get() = _favoriteStories

    // Функция для добавления или удаления истории из избранного
    fun toggleFavorite(story: Story) {
        if (_favoriteStories.contains(story)) {
            _favoriteStories.remove(story)
        } else {
            _favoriteStories.add(story)
        }
    }

    // Проверка, находится ли история в избранном
    fun isFavorite(story: Story): Boolean {
        return _favoriteStories.contains(story)
    }
}