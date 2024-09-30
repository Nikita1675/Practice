package com.example.ufanet_practice.ui_component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ufanet_practice.data.Story

// Определим одинаковую форму для карточек и изображений
val commonRoundedCornerShape = RoundedCornerShape(8.dp)

@Composable
fun StoriesGrid(stories: List<Story>) {
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
                        StoryItem(story)
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

@Composable
fun StoryItem(story: Story) {
    val context = LocalContext.current // Получаем контекст

    Card(
        modifier = Modifier
            .aspectRatio(1f) // Соотношение сторон 1:1 для квадрата
            .padding(6.dp),  // Отступы между карточками
        shape = commonRoundedCornerShape // Закругляем края карточки
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp) // Внутренние отступы в карточке
        ) {
            // Картинка вверху, с закругленными углами
            Image(
                painter = rememberAsyncImagePainter(
                    model = story.imageLogo ?: "https://via.placeholder.com/150"
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Увеличьте высоту, если нужно
                    .clip(commonRoundedCornerShape) // Закругляем углы изображения с тем же радиусом
                    .clickable {
                        // Переход по ссылке при нажатии на картинку
                        story.url?.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                contentScale = ContentScale.Crop // Заполняем область, обрезая правую сторону
            )
            // Отображаем название истории с отступом
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Распределяем пространство между текстом и кнопкой
            ) {
                Text(
                    text = story.newsName ?: "Без названия",
                    modifier = Modifier
                        .padding(top = 5.dp) // Внутренний отступ текста
                        .weight(1f), // Используем вес, чтобы текст занимал все доступное пространство
                    maxLines = 2 // Ограничиваем количество строк
                )
                // Добавляем кнопку избранного с отступами
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 26.dp) // Отступы слева и сверху для кнопки
                ) {
                    FavoriteButton(
                        isFavorite = story.isFavorite,
                        onToggleFavorite = { /* Логика изменения избранного */ }
                    )
                }
            }
        }
    }
}
