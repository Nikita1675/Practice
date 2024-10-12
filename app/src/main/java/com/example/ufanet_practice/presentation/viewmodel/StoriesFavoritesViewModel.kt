package com.example.ufanet_practice.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet_practice.domain.model.Story
import com.example.ufanet_practice.presentation.model.StoryUiModel
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoriesFavoritesViewModel(
    application: Application,
    private val getStoriesUseCase: GetStoriesUseCase
) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Локальные массивы для хранения историй и избранных историй
    private val _originalStories = MutableStateFlow<List<StoryUiModel>>(emptyList())
    private val _filteredStories = MutableStateFlow<List<StoryUiModel>>(emptyList())
    private val _favoriteStories = mutableStateListOf<StoryUiModel>()

    val stories: StateFlow<List<StoryUiModel>> = _filteredStories

    init {
        fetchStories()
        loadFavorites()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            val storiesList = getStoriesUseCase() // Получаем список Story
            _originalStories.value = storiesList.map { story ->
                StoryUiModel(
                    newsName = story.newsName,
                    imageLogo = story.imageLogo,
                    url = story.url,
                    isFavorite = isFavorite(StoryUiModel( // Убедитесь, что здесь используется StoryUiModel
                        newsName = story.newsName,
                        imageLogo = story.imageLogo,
                        url = story.url,
                        isFavorite = false, // Изначально не является избранным
                        uniqueName = story.uniqueName
                    )),
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

    fun toggleFavorite(story: StoryUiModel) {
        if (_favoriteStories.contains(story)) {
            _favoriteStories.remove(story)
        } else {
            _favoriteStories.add(story)
        }
        saveFavorites()
    }

    fun isFavorite(story: StoryUiModel): Boolean {
        return _favoriteStories.contains(story)
    }

    private fun saveFavorites() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(_favoriteStories)
        editor.putString("favorite_stories", json)
        editor.apply()
    }

    private fun loadFavorites() {
        val json = sharedPreferences.getString("favorite_stories", null)
        if (json != null) {
            val type = object : TypeToken<List<StoryUiModel>>() {}.type
            val savedFavorites: List<StoryUiModel> = gson.fromJson(json, type)
            _favoriteStories.clear() // Очищаем текущий список избранного
            _favoriteStories.addAll(savedFavorites) // Загружаем сохраненные избранные истории
        }
    }
}
