package com.example.ufanet_practice.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.ufanet_practice.presentation.ui.StoriesScreen
import dagger.hilt.android.AndroidEntryPoint
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val favoritesStoryViewModel: FavoritesStoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StoriesScreen(favoritesStoryViewModel = favoritesStoryViewModel)
        }
    }
}
