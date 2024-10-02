package com.example.ufanet_practice.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ufanet_practice.domain.model.Story
import com.example.ufanet_practice.presentation.viewmodel.FavoritesViewModel

val commonRoundedCornerShape = RoundedCornerShape(8.dp)

@Composable
fun StoryItem(story: Story, favoritesViewModel: FavoritesViewModel) {
    val context = LocalContext.current // Получаем контекст

    Card(
        modifier = Modifier
            .aspectRatio(1f) // Соотношение сторон 1:1 для квадрата
            .padding(6.dp),  // Отступы между карточками
        shape = commonRoundedCornerShape // Закругляю края карточки
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
                    .height(100.dp)
                    .clip(commonRoundedCornerShape) // Закругляет углы изображения с тем же радиусом
                    .clickable {
                        // Переход по ссылке при нажатии на картинку
                        story.url?.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                contentScale = ContentScale.Crop // Заполняет область, обрезая правую сторону
            )
            // Отображает название истории с отступом
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Распределяет пространство между текстом и кнопкой
            ) {
                Text(
                    text = story.newsName ?: "Без названия",
                    modifier = Modifier
                        .padding(top = 5.dp) // Внутренний отступ текста
                        .weight(1f), // Использует вес, чтобы текст занимал все доступное пространство
                    maxLines = 2 // Ограничивает количество строк
                )
                // Добавляю кнопку избранного с отступами
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 26.dp) // Отступы слева и сверху для кнопки
                ) {
                    // Проверяем, находится ли история в избранном
                    val isFavorite = remember { favoritesViewModel.isFavorite(story) }

                    FavoriteButton(
                        isFavorite = isFavorite,
                        onToggleFavorite = {
                            favoritesViewModel.toggleFavorite(story)
                        }
                    )
                }
            }
        }
    }
}
