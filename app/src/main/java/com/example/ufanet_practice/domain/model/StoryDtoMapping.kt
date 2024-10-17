package com.example.ufanet_practice.domain.model

import com.example.ufanet_practice.data.model.StoryDto

// Функция расширения для маппинга StoryDto в Story
fun StoryDto.mapToDomain(): Story {
    return Story(
        newsName = this.newsName,
        imageLogo = this.imageLogo,
        url = this.url,
        isFavorite = this.isFavorite,
        uniqueName = this.uniqueName
    )
}
