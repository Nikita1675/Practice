package com.example.ufanet_practice.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.presentation.model.StoryPresentation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesStoryViewModel @Inject constructor(
    application: Application,
    private val getStoriesUseCase: GetStoriesUseCase
) : AndroidViewModel(application) {

    // Константы для SharedPreferences
    companion object {
        private const val PREFS_NAME = "favorites_prefs"
        private const val FAVORITE_STORIES_KEY = "favorite_stories"
    }

    private val sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
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
        if (currentFavorites.any { it.uniqueName == story.uniqueName }) {
            currentFavorites.removeAll { it.uniqueName == story.uniqueName } // Удаляем из избранного
        } else {
            currentFavorites.add(story) // Добавляем в избранное
        }
        _favoriteStories.value = currentFavorites // Обновляем состояние
        saveFavorites() // Сохраняем изменения в SharedPreferences

        // Обновляем состояние всех историй
        _originalStories.value = _originalStories.value.map {
            if (it.uniqueName == story.uniqueName) {
                it.copy(isFavorite = !it.isFavorite) // Обновляем состояние isFavorite
            } else {
                it
            }
        }

        // Обновляем фильтрованные истории, если в них содержится измененная история
        _filteredStories.value = _filteredStories.value.map {
            if (it.uniqueName == story.uniqueName) {
                it.copy(isFavorite = !it.isFavorite) // Обновляем состояние isFavorite
            } else {
                it
            }
        }
    }

    fun isFavorite(story: StoryPresentation): Boolean {
        return _favoriteStories.value.any { it.uniqueName == story.uniqueName }
    }

    private fun saveFavorites() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(_favoriteStories.value)
        editor.putString(FAVORITE_STORIES_KEY, json)
        editor.apply()
    }

    private fun loadFavorites() {
        val json = sharedPreferences.getString(FAVORITE_STORIES_KEY, null)
        if (json != null) {
            val type = object : TypeToken<List<StoryPresentation>>() {}.type
            val savedFavorites: List<StoryPresentation> = gson.fromJson(json, type)
            _favoriteStories.value = savedFavorites // Сохраняем в состоянии
        }
    }
}
