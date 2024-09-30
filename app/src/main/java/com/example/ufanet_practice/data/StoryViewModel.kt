package com.example.ufanet_practice.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoriesViewModel : ViewModel() {
    private val _originalStories = MutableStateFlow<List<Story>>(emptyList())  // Оригинальный список историй
    private val _filteredStories = MutableStateFlow<List<Story>>(emptyList())  // Фильтрованный список

    val stories: StateFlow<List<Story>> = _filteredStories

    init {
        fetchStories()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            try {
                Log.d("StoriesViewModel", "Fetching stories from API...")

                val response = RetrofitInstance.api.getStories()

                if (response.isSuccessful) {
                    val storiesList = response.body()?.detail?.stories ?: emptyList()
                    Log.d("StoriesViewModel", "Fetched stories: $storiesList")

                    _originalStories.value = storiesList
                    _filteredStories.value = storiesList  // Отображаем полный список до фильтрации
                } else {
                    Log.e("StoriesViewModel", "Failed to fetch stories: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("StoriesViewModel", "Error fetching stories: ${e.message}", e)
            }
        }
    }

    // Фильтрация списка по ключевому слову
    fun filterStories(query: String) {
        if (query.isBlank()) {
            _filteredStories.value = _originalStories.value  // Если строка поиска пуста, показываем оригинальный список
        } else {
            val filteredStories = _originalStories.value.filter {
                it.newsName?.contains(query, ignoreCase = true) == true
            }
            _filteredStories.value = filteredStories
        }
        Log.d("StoriesViewModel", "Filtered stories: ${_filteredStories.value}")
    }
}


