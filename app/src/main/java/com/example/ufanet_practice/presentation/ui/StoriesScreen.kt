package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ufanet_practice.presentation.viewmodel.StoriesFavoritesViewModel

@Composable
fun StoriesScreen(viewModel: StoriesFavoritesViewModel) {
    val stories by viewModel.stories.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    Column {
        // Передача searchQuery и viewModel в компонент поиска
        SearchBarComponent(searchText = searchQuery, viewModel = viewModel)
        // Передача только одной viewModel в StoriesGrid
        StoriesGrid(stories = stories, viewModel = viewModel)
    }
}
