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
    private lateinit var favoritesViewModel: FavoritesViewModel // Добавляем ViewModel для избранного

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализируем необходимые зависимости
        val repository = StoriesRepository()
        val getStoriesUseCase = GetStoriesUseCase(repository)
        val viewModelFactory = StoriesViewModelFactory(getStoriesUseCase)

        // Получаем экземпляр ViewModel через фабрику
        storiesViewModel = ViewModelProvider(this, viewModelFactory).get(StoriesViewModel::class.java)

        // Инициализируем ViewModel для избранного
        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        setContent {
            // Передаем оба ViewModel в StoriesScreen
            StoriesScreen(
                storiesViewModel = storiesViewModel, // Передаем storiesViewModel
                favoritesViewModel = favoritesViewModel // Передаем favoritesViewModel
            )
        }
    }
}
