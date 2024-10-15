package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ufanet_practice.presentation.model.StoryPresentation
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModel

@Composable
fun StoriesGrid(
    stories: List<StoryPresentation>,
    isFavorite: (StoryPresentation) -> Boolean, // Лямбда для проверки избранного
    onToggleFavorite: (StoryPresentation) -> Unit // Лямбда для переключения избранного
) {
    LazyColumn(
        modifier = Modifier.padding(15.dp)
    ) {
        items(stories.chunked(2)) { pair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                pair.forEach { story ->
                    Box(modifier = Modifier.weight(1f)) {
                        StoryItem(
                            story = story,
                            isFavorite = { isFavorite(story) }, // Передаем лямбда для каждого элемента
                            onToggleFavorite = { onToggleFavorite(story) } // Лямбда для переключения
                        )
                    }
                }
                if (pair.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

