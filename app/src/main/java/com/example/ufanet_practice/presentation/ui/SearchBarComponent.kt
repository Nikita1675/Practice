package com.example.ufanet_practice.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ufanet_practice.data.viewmodel.StoriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    searchText: MutableState<String>,
    viewModel: StoriesViewModel,  // Передаём ViewModel в компонент
    modifier: Modifier = Modifier
) {
    SearchBar(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(6.dp))  // Устанавливаем фон до обводки
            .border(
                width = 2.dp,  // Ширина обводки
                color = Color.Red,  // Цвет обводки
                shape = RoundedCornerShape(6.dp)  // Закругленные углы
            ),
        colors = SearchBarDefaults.colors(
            containerColor = Color.White  // Задаем белый фон, чтобы выделить обводку
        ),
        query = searchText.value,
        onQueryChange = { text ->
            searchText.value = text
            viewModel.filterStories(text)  // Вызываем фильтрацию при изменении текста
        },
        onSearch = {},
        placeholder = {
            Text(text = "Поиск")
        },
        shape = RoundedCornerShape(6.dp),
        active = false,
        onActiveChange = {}
    ) {}
}
