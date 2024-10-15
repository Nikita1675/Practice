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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ufanet_practice.R
import com.example.ufanet_practice.presentation.model.StoryPresentation
import com.example.ufanet_practice.presentation.viewmodel.FavoritesStoryViewModel

val commonRoundedCornerShape = RoundedCornerShape(8.dp)

@Composable
fun StoryItem(
    story: StoryPresentation,
    isFavorite: () -> Boolean, // Передаем лямбда для проверки избранного
    onToggleFavorite: () -> Unit // Передаем лямбда для переключения избранного
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
                    text = story.newsName ?: stringResource(id = R.string.no_title),
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .weight(1f),
                    maxLines = 2
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp, top = 20.dp, end = 7.dp)
                ) {
                    FavoriteButton(
                        isFavorite = isFavorite(), // лямбда-функцию для проверки избранного
                        onToggleFavorite = onToggleFavorite // лямбда для переключения избранного
                    )
                }
            }
        }
    }
}

