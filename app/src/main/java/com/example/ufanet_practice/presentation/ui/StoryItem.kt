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
fun StoryItem(
    story: Story,
    favoritesViewModel: FavoritesViewModel // Передача ViewModel избранного
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(6.dp),
        shape = commonRoundedCornerShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = story.imageLogo ?: "https://via.placeholder.com/150"
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(commonRoundedCornerShape)
                    .clickable {
                        story.url?.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                    },
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = story.newsName ?: "Без названия",
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .weight(1f),
                    maxLines = 2
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp, top = 20.dp, end = 7.dp)
                ) {
                    FavoriteButton(
                        isFavorite = favoritesViewModel.isFavorite(story),
                        onToggleFavorite = {
                            favoritesViewModel.toggleFavorite(story)
                        }
                    )
                }
            }
        }
    }
}
