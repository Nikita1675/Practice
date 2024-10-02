package com.example.ufanet_practice.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Добавление истории в избранное
    fun addToFavorites(storyId: String) {
        val favorites = getFavorites().toMutableSet()
        favorites.add(storyId)
        saveFavorites(favorites)
    }

    // Удаление истории из избранного
    fun removeFromFavorites(storyId: String) {
        val favorites = getFavorites().toMutableSet()
        favorites.remove(storyId)
        saveFavorites(favorites)
    }

    // Проверка, является ли история избранной
    fun isFavorite(storyId: String): Boolean {
        return getFavorites().contains(storyId)
    }

    // Получение списка избранных историй
    private fun getFavorites(): Set<String> {
        val json = sharedPreferences.getString("favorites", null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<Set<String>>() {}.type) ?: emptySet()
        } else {
            emptySet()
        }
    }

    // Сохранение списка избранных историй
    private fun saveFavorites(favorites: Set<String>) {
        sharedPreferences.edit().putString("favorites", gson.toJson(favorites)).apply()
    }
}
