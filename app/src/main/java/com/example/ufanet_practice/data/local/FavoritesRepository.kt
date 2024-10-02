package com.example.ufanet_practice.data.local

class FavoritesRepository {
    private val favoritesIds = mutableSetOf<Int>() // Множество для хранения ID избранных историй

    fun addFavorite(id: Int) {
        favoritesIds.add(id)
    }

    fun removeFavorite(id: Int) {
        favoritesIds.remove(id)
    }

    fun isFavorite(id: Int): Boolean {
        return favoritesIds.contains(id)
    }

    fun getFavorites(): Set<Int> {
        return favoritesIds
    }
}
