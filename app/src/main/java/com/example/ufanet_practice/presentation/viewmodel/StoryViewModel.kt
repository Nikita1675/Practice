package com.example.ufanet_practice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.domain.model.Story
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoriesViewModel(private val getStoriesUseCase: GetStoriesUseCase) : ViewModel() {
    private val _originalStories = MutableStateFlow<List<Story>>(emptyList())
    private val _filteredStories = MutableStateFlow<List<Story>>(emptyList())

    val stories: StateFlow<List<Story>> = _filteredStories

    init {
        fetchStories()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            val storiesList = getStoriesUseCase()
            _originalStories.value = storiesList
            _filteredStories.value = storiesList
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
}
