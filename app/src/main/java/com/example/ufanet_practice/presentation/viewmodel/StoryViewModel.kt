package com.example.ufanet_practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.domain.model.Story
import com.example.ufanet_practice.presentation.model.StoryPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoriesViewModel(private val getStoriesUseCase: GetStoriesUseCase) : ViewModel() {
    private val _originalStories = MutableStateFlow<List<StoryPresentation>>(emptyList()) // Список для хранения оригинальных историй
    private val _filteredStories = MutableStateFlow<List<StoryPresentation>>(emptyList()) // Список для хранения фильтрованных историй

    val stories: StateFlow<List<StoryPresentation>> = _filteredStories // Возвращаем фильтрованные истории

    init {
        fetchStories()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            val storiesList = getStoriesUseCase() // Получаем истории из доменного слоя
            // Преобразуем в StoryPresentation и сохраняем в оригинальном списке
            _originalStories.value = storiesList.map { story ->
                StoryPresentation(
                    newsName = story.newsName,
                    imageLogo = story.imageLogo,
                    url = story.url,
                    isFavorite = story.isFavorite,
                    uniqueName = story.uniqueName
                )
            }
            // Копируем оригинальные истории в фильтрованные
            _filteredStories.value = _originalStories.value
        }
    }

    fun filterStories(query: String) {
        _filteredStories.value = if (query.isBlank()) {
            _originalStories.value // Если запрос пустой, возвращаем оригинальные истории
        } else {
            _originalStories.value.filter {
                it.newsName?.contains(query, ignoreCase = true) == true // Фильтруем по имени новости
            }
        }
    }
}
