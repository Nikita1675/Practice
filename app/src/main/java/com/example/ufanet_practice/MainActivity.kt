package com.example.ufanet_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ufanet_practice.data.StoriesViewModel
import com.example.ufanet_practice.data.Story
import com.example.ufanet_practice.ui_component.SearchBarComponent

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
    val stories by viewModel.stories.collectAsState()
    var searchQuery = remember { mutableStateOf("") }

    Column {
        // Вызов SearchBarComponent
        SearchBarComponent(searchText = searchQuery)

        // Отображаем только отфильтрованные истории, проверяем, что newsName не null
        StoriesGrid(stories = stories.filter { it.newsName?.contains(searchQuery.value, ignoreCase = true) == true })
    }
}

@Composable
fun StoriesGrid(stories: List<Story>) {
    // LazyColumn для создания вертикального списка
    LazyColumn {
        // Разбиваем на 2 строки список
        items(stories.chunked(1)) { pair ->
            Row(modifier = Modifier.fillMaxWidth()) {
                pair.forEach { story ->
                    StoryItem(story)
                }
            }
        }
    }
}

@Composable
fun StoryItem(story: Story) {
    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column {
            // Coil для загрузки изображений, проверка на null и замена на картинку-заглушку
            Image(
                painter = rememberAsyncImagePainter(
                    model = story.imageLogo ?: "https://via.placeholder.com/150"
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // Установите желаемую высоту для изображения
            )
            // Название истории, проверка на null и замена на текст по умолчанию
            Text(
                text = story.newsName ?: "Без названия",
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}
