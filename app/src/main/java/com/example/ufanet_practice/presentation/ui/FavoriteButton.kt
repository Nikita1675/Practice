package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ufanet_practice.R

@Composable
fun FavoriteButton(isFavorite: Boolean, onToggleFavorite: () -> Unit) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clickable { onToggleFavorite() }
    ) {
        Image(
            painter = painterResource(id = if (isFavorite) R.drawable.state_on else R.drawable.state_off),
            contentDescription = stringResource(id = if (isFavorite) R.string.favorite else R.string.not_favorite),
            modifier = Modifier.size(50.dp),
            colorFilter = ColorFilter.tint(if (isFavorite) Color.Red else Color.Gray)
        )
    }
}
