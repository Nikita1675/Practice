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
    // Подписывает на список историй из ViewModel
    val stories by storiesViewModel.stories.collectAsState()

    // Состояние для текста поиска
    val searchQuery = remember { mutableStateOf("") }

    Column {
        // Вызов компонента поиска и передача ViewModel для фильтрации
        SearchBarComponent(searchText = searchQuery, viewModel = storiesViewModel)

        // Отображение отфильтрованных историй, которые уже фильтруются в ViewModel
        StoriesGrid(stories = stories, favoritesViewModel = favoritesViewModel)
    }
}
