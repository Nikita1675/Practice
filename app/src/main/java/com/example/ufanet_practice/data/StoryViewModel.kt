package com.example.ufanet_practice.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
//отвечает за хранение и управление данными
class StoriesViewModel : ViewModel() {
    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> = _stories

    init {
        fetchStories()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getStories()
            if (response.isSuccessful) {
                _stories.value = response.body()?.stories ?: emptyList()
            }
        }
    }
    // Фильтрация списка по ключевому слову
    fun filterStories(query: String) {
        val filteredStories = _stories.value.filter { it.newsName.contains(query, ignoreCase = true) }
        _stories.value = filteredStories
    }
}


