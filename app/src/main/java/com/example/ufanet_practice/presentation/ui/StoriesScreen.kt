package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ufanet_practice.presentation.viewmodel.FavoritesViewModel
import com.example.ufanet_practice.presentation.viewmodel.StoriesViewModel

@Composable
fun StoriesScreen(storiesViewModel: StoriesViewModel, favoritesViewModel: FavoritesViewModel) {
    val stories by storiesViewModel.stories.collectAsState()

    val searchQuery = remember { mutableStateOf("") }

    Column {
        SearchBarComponent(searchText = searchQuery, viewModel = storiesViewModel)
        StoriesGrid(stories = stories, favoritesViewModel = favoritesViewModel)
    }
}
