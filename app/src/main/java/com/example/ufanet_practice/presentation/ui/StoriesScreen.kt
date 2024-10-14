package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModel
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

@Composable
fun StoriesScreen(favoritesStoryViewModel: FavoritesStoryViewModel) {
    // фильтрованные истории
    val stories by favoritesStoryViewModel.stories.collectAsState()

    // Состояние для поиска
    val searchQuery = remember { mutableStateOf("") }

    // Scaffold для топбара и контента
    Scaffold(
        topBar = {
            SearchBarComponent(searchText = searchQuery, viewModel = favoritesStoryViewModel)
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                StoriesGrid(stories = stories, favoritesViewModel = favoritesStoryViewModel)
            }
        }
    )
}
