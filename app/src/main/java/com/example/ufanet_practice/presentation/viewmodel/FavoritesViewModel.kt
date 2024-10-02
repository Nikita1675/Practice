package com.example.ufanet_practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.ufanet_practice.domain.model.Story

class FavoritesViewModel : ViewModel() {
    // Локальный массив для хранения избранных историй
    private val _favoriteStories = mutableStateListOf<Story>()
    val favoriteStories: List<Story> get() = _favoriteStories

    // Метод для добавления или удаления истории из избранного
    fun toggleFavorite(story: Story) {
        if (_favoriteStories.contains(story)) {
            _favoriteStories.remove(story) // Удаляем из избранного, если уже есть
        } else {
            _favoriteStories.add(story) // Добавляем в избранное, если нет
        }
    }

    // Проверяем, находится ли история в избранном
    fun isFavorite(story: Story): Boolean {
        return _favoriteStories.contains(story)
    }
}
