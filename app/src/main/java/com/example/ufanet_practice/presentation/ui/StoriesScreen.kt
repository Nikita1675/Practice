package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModel

@Composable
fun StoriesScreen(favoritesStoryViewModel: FavoritesStoryViewModel) {
    // Получаем фильтрованные истории
    val stories by favoritesStoryViewModel.stories.collectAsState()

    // Состояние для поиска
    val searchQuery = remember { mutableStateOf("") }

    // UI-компоненты
    Column {
        SearchBarComponent(searchText = searchQuery, viewModel = favoritesStoryViewModel)
        StoriesGrid(stories = stories, favoritesViewModel = favoritesStoryViewModel)
    }
}
