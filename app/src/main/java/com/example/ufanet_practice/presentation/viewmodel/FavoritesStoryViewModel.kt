package com.example.ufanet_practice.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet_practice.domain.model.Story
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.presentation.model.StoryPresentation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesStoryViewModel(
    application: Application,
    private val getStoriesUseCase: GetStoriesUseCase
) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val _originalStories = MutableStateFlow<List<StoryPresentation>>(emptyList())
    private val _filteredStories = MutableStateFlow<List<StoryPresentation>>(emptyList())
    private val _favoriteStories = MutableStateFlow<List<StoryPresentation>>(emptyList())

    val stories: StateFlow<List<StoryPresentation>> = _filteredStories
    val favoriteStories: StateFlow<List<StoryPresentation>> = _favoriteStories

    init {
        loadFavorites() // Загружаем избранные истории при инициализации ViewModel
        fetchStories() // Загружаем истории
    }

    private fun fetchStories() {
        viewModelScope.launch {
            val storiesList = getStoriesUseCase() // Получаем истории из доменного слоя
            _originalStories.value = storiesList.map { story ->
                StoryPresentation(
                    newsName = story.newsName,
                    imageLogo = story.imageLogo,
                    url = story.url,
                    isFavorite = story.isFavorite,
                    uniqueName = story.uniqueName
                )
            }
            _filteredStories.value = _originalStories.value
        }
    }

    fun filterStories(query: String) {
        _filteredStories.value = if (query.isBlank()) {
            _originalStories.value
        } else {
            _originalStories.value.filter {
                it.newsName?.contains(query, ignoreCase = true) == true
            }
        }
    }

    fun toggleFavorite(story: StoryPresentation) {
        val currentFavorites = _favoriteStories.value.toMutableList()
        if (currentFavorites.contains(story)) {
            currentFavorites.remove(story) // Удаляем из избранного
        } else {
            currentFavorites.add(story) // Добавляем в избранное
        }
        _favoriteStories.value = currentFavorites // Обновляем состояние
        saveFavorites() // Сохраняем изменения в SharedPreferences
    }

    fun isFavorite(story: StoryPresentation): Boolean {
        return _favoriteStories.value.contains(story)
    }

    private fun saveFavorites() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(_favoriteStories.value)
        editor.putString("favorite_stories", json)
        editor.apply()
    }

    private fun loadFavorites() {
        val json = sharedPreferences.getString("favorite_stories", null)
        if (json != null) {
            val type = object : TypeToken<List<StoryPresentation>>() {}.type
            val savedFavorites: List<StoryPresentation> = gson.fromJson(json, type)
            _favoriteStories.value = savedFavorites // Сохраняем в состоянии
        }
    }
}
