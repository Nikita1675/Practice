package com.example.ufanet_practice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.ufanet_practice.domain.usecase.GetStoriesUseCase
import com.example.ufanet_practice.data.repository.StoriesRepository
import com.example.ufanet_practice.presentation.ui.StoriesScreen
import com.example.ufanet_practice.presentation.viewmodel.StoriesFavoritesViewModel
import com.example.ufanet_practice.presentation.viewmodel.StoriesFavoritesViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var storiesFavoritesViewModel: StoriesFavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация репозитория и use case
        val repository = StoriesRepository()
        val getStoriesUseCase = GetStoriesUseCase(repository)
        val viewModelFactory = StoriesFavoritesViewModelFactory(application, getStoriesUseCase)

        // Инициализация ViewModel
        storiesFavoritesViewModel = ViewModelProvider(this, viewModelFactory).get(StoriesFavoritesViewModel::class.java)

        setContent {
            // Передача ViewModel в StoriesScreen
            StoriesScreen(storiesFavoritesViewModel)
        }
    }
}
