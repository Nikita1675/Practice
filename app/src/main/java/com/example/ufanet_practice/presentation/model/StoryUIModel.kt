package com.example.ufanet_practice.presentation.model

data class StoryUiModel(
    val newsName: String?,   // Название новости
    val imageLogo: String?,  // URL логотипа/изображения
    val url: String?,        // Ссылка на полную историю
    val isFavorite: Boolean,  // Флаг, показывающий, является ли история избранной
    val uniqueName: String?   // Уникальный идентификатор истории
)
