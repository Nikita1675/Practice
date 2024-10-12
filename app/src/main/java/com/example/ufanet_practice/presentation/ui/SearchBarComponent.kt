package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ufanet_practice.R
import com.example.ufanet_practice.presentation.viewmodel.StoriesFavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    searchText: MutableState<String>,
    viewModel: StoriesFavoritesViewModel,  // Изменено на объединенную ViewModel
    modifier: Modifier = Modifier
) {
    var isActive by remember { mutableStateOf(false) }  // Состояние активного поиска

    SearchBar(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors = SearchBarDefaults.colors(
            containerColor = Color.LightGray  // Белый фон, чтобы выделить обводку
        ),
        query = searchText.value,
        onQueryChange = { text ->
            searchText.value = text
            viewModel.filterStories(text)  // Вызывает фильтрацию при изменении текста
        },
        onSearch = { isActive = false },  // Действие при поиске
        placeholder = {
            Text(text = stringResource(id = R.string.search_hint))
        },
        shape = RoundedCornerShape(6.dp),
        active = isActive,  // Устанавливаем состояние активности
        onActiveChange = { isActive = it }  // Изменяем состояние активности
    ) {}
}
