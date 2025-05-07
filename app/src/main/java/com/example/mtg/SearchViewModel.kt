package com.example.mtg

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _cards = MutableStateFlow<List<MagicCard>>(emptyList())
    val cards: StateFlow<List<MagicCard>> = _cards

    fun findCards(filters: CardFilters) {
        viewModelScope.launch {
            val query = buildString {
                if (filters.name.isNotBlank()) {
                    val name = if (filters.name.trim().contains(" "))
                        "\"${filters.name.trim()}\"" else filters.name.trim()
                    append("name:$name ")
                }

                if (filters.set.isNotBlank()) append("set:${filters.set}")

                val colorKey = ColorToApi.translateColor(filters.color)
                if (colorKey.isNotBlank()) {
                    append("color:$colorKey ")
                }

                val rarityKey = RarityToApi.translateRarity(filters.rarity)
                if (rarityKey.isNotBlank()) {
                    append("rarity:$rarityKey ")
                }

                val typeKey = TypeToApi.translateType(filters.type)
                if (typeKey.isNotBlank()) {
                    append("type:$typeKey ")
                }

                if (filters.manaCost.isNotBlank()) append("mana:${filters.manaCost}")
            }.trim()

            Log.d("QUERY_DEBUG", "Query final: '$query'")

            try {
                val response = RetrofitClient.api.searchCards(query)
                _cards.value = response.data
            } catch (e: Exception) {
                Log.e("API_ERROR", "Erro ao buscar cartas", e)
            }
        }
    }
}