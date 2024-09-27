package com.example.ufanet_practice.ui_component

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    searchText: MutableState<String>,
    modifier: Modifier = Modifier
) {
    SearchBar(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            //.border(width = 3.dp, color = Color.Gray, shape = RoundedCornerShape(6.dp))
        ,

        colors = SearchBarDefaults.colors(
            containerColor = Color.White
        ),
        query = searchText.value,
        onQueryChange = { text -> searchText.value = text },
        onSearch = {},
        placeholder = {
            Text(text = "Поиск")
        },
        shape = RoundedCornerShape(6.dp),
        active = false,
        onActiveChange = {}
    ) {}
}
