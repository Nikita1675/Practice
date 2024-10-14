package com.example.ufanet_practice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.presentation.ui.StoriesScreen
import com.example.ufanet_practice.presentation.viewmodel.FavoritesViewModel
import com.example.ufanet_practice.presentation.viewmodel.StoriesViewModel
import com.example.ufanet_practice.presentation.viewmodel.StoriesViewModelFactory
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.data.repository.StoriesRepository

class MainActivity : ComponentActivity() {
    private lateinit var storiesViewModel: StoriesViewModel
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация репозитория и use case
        val repository = StoriesRepository()
        val getStoriesUseCase = GetStoriesUseCase(repository)

        // Передача контекста при создании фабрики
        val viewModelFactory = StoriesViewModelFactory(getStoriesUseCase, application)

        // Инициализация ViewModel для историй через фабрику
        storiesViewModel = ViewModelProvider(this, viewModelFactory).get(StoriesViewModel::class.java)

        // Инициализация ViewModel для избранного с передачей application
        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        setContent {
            StoriesScreen(
                storiesViewModel = storiesViewModel,
                favoritesViewModel = favoritesViewModel
            )
        }
    }
}
