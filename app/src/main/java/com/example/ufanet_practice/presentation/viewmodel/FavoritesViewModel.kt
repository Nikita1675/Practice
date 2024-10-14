package com.example.ufanet_practice.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.ufanet_practice.presentation.model.StoryPresentation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Локальный массив для хранения избранных историй
    private val _favoriteStories = mutableStateListOf<StoryPresentation>()

    init {
        loadFavorites() // Загружаем избранные истории при инициализации ViewModel
    }

    // Метод для добавления или удаления истории из избранного
    fun toggleFavorite(story: StoryPresentation) {
        if (_favoriteStories.contains(story)) {
            _favoriteStories.remove(story) // Удаляет из избранного, если уже есть
        } else {
            _favoriteStories.add(story) // Добавляет в избранное, если нет
        }
        saveFavorites() // Сохраняем изменения в SharedPreferences
    }

    // Проверяет, находится ли история в избранном
    fun isFavorite(story: StoryPresentation): Boolean {
        return _favoriteStories.contains(story)
    }

    // Сохраняет список избранных историй в SharedPreferences
    private fun saveFavorites() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(_favoriteStories)
        editor.putString("favorite_stories", json)
        editor.apply()
    }

    // Загружает избранные истории из SharedPreferences
    private fun loadFavorites() {
        val json = sharedPreferences.getString("favorite_stories", null)
        if (json != null) {
            val type = object : TypeToken<List<StoryPresentation>>() {}.type
            val savedFavorites: List<StoryPresentation> = gson.fromJson(json, type)
            _favoriteStories.addAll(savedFavorites)
        }
    }
}
