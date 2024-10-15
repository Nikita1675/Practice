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
    val stories by favoritesStoryViewModel.stories.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBarComponent(
                searchText = searchQuery,
                onSearchQueryChanged = { query ->
                    favoritesStoryViewModel.filterStories(query) // Передаем фильтрацию
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                StoriesGrid(
                    stories = stories,
                    isFavorite = { story ->
                        favoritesStoryViewModel.isFavorite(story) // Лямбда для проверки избранного
                    },
                    onToggleFavorite = { story ->
                        favoritesStoryViewModel.toggleFavorite(story) // Лямбда для переключения избранного
                    }
                )
            }
        }
    )
}
