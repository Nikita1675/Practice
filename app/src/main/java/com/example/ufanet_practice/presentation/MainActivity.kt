package com.example.ufanet_practice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.presentation.ui.StoriesScreen
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.data.repository.StoriesRepository
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModel
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var favoritesStoryViewModel: FavoritesStoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация репозитория и use case
        val repository = StoriesRepository()
        val getStoriesUseCase = GetStoriesUseCase(repository)

        // Передача контекста при создании фабрики
        val viewModelFactory = FavoritesStoryViewModelFactory(getStoriesUseCase, application)

        // Инициализация ViewModel для историй и избранного через фабрику
        favoritesStoryViewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesStoryViewModel::class.java)

        setContent {
            StoriesScreen(
                favoritesStoryViewModel = favoritesStoryViewModel // Передаем объединенный ViewModel
            )
        }
    }
}

