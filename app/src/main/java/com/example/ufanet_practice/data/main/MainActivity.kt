package com.example.ufanet_practice.data.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ufanet_practice.data.viewmodel.StoriesViewModel
import com.example.ufanet_practice.data.ui.StoriesGrid
import com.example.ufanet_practice.data.ui.SearchBarComponent

class MainActivity : ComponentActivity() {
    private val storiesViewModel: StoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoriesScreen(storiesViewModel)
        }
    }
}

@Composable
fun StoriesScreen(viewModel: StoriesViewModel) {
    // Подписываемся на список историй из ViewModel
    val stories by viewModel.stories.collectAsState()

    // Состояние для текста поиска
    val searchQuery = remember { mutableStateOf("") }

    Column {
        // Вызов компонента поиска и передача ViewModel для фильтрации
        SearchBarComponent(searchText = searchQuery, viewModel = viewModel)

        // Отображение отфильтрованных историй, которые уже фильтруются в ViewModel
        StoriesGrid(stories = stories)
    }
}

