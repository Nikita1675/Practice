package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ufanet_practice.domain.model.Story
import com.example.ufanet_practice.presentation.model.StoryPresentation
import com.example.ufanet_practice.presentation.viewmodel.FavoritesViewModel

@Composable
fun StoriesGrid(stories: List<StoryPresentation>, favoritesViewModel: FavoritesViewModel) {
    LazyColumn(
        modifier = Modifier
            .padding(15.dp) // Одинаковые отступы от краёв экрана
    ) {
        items(stories.chunked(2)) { pair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp), // Отступы между строками
                horizontalArrangement = Arrangement.spacedBy(1.dp) // Отступы между элементами в строке
            ) {
                pair.forEach { story ->
                    Box(modifier = Modifier.weight(1f)) {
                        StoryItem(story, favoritesViewModel) // Передаём StoryPresentation
                    }
                }

                // Проверка на нечетное количество элементов
                if (pair.size < 2) {
                    Spacer(modifier = Modifier.weight(1f)) // Заполнение пустого пространства
                }
            }
        }
    }
}
