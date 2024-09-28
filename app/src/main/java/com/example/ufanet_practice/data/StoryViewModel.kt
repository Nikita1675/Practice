package com.example.ufanet_practice.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoriesViewModel : ViewModel() {
    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> = _stories

    init {
        fetchStories()
    }

    private fun fetchStories() {
        viewModelScope.launch {
            try {
                Log.d("StoriesViewModel", "Fetching stories from API...")

                val response = RetrofitInstance.api.getStories()

                // Логи
                Log.d("StoriesViewModel", "API response: ${response.code()}")

                if (response.isSuccessful) {
                    // Изменение: получение списка через обёртку "detail"
                    val storiesList = response.body()?.detail?.stories ?: emptyList()
                    Log.d("StoriesViewModel", "Fetched stories: $storiesList")

                    _stories.value = storiesList
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
        val filteredStories = _stories.value.filter {
            it.newsName?.contains(query, ignoreCase = true) == true // Проверка на null перед contains()
        }
        _stories.value = filteredStories
        Log.d("StoriesViewModel", "Filtered stories: $filteredStories")
    }

}



