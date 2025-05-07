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
            val query = QueryBuilder.build(filters)

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