package com.example.mtg.searchActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtg.call.query.useCases.ColorQueryUseCase
import com.example.mtg.call.query.useCases.MCostQueryUseCase
import com.example.mtg.call.MagicCard
import com.example.mtg.call.query.useCases.RarityQueryUseCase
import com.example.mtg.call.RetrofitClient
import com.example.mtg.call.query.useCases.NameQueryUseCase
import com.example.mtg.call.query.set.SetQueryUseCase
import com.example.mtg.call.query.useCases.TypeQueryUseCase
import com.example.mtg.filterActivity.CardFilters
import com.example.mtg.call.query.QueryBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val queryBuilder = QueryBuilder(
        NameQueryUseCase(),
        SetQueryUseCase(),
        ColorQueryUseCase(),
        RarityQueryUseCase(),
        TypeQueryUseCase(),
        MCostQueryUseCase()
    )

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _cards = MutableStateFlow<List<MagicCard>>(emptyList())
    val cards: StateFlow<List<MagicCard>> = _cards

    fun findCards(filters: CardFilters) {
        viewModelScope.launch {
            _isLoading.value = true
            val query = queryBuilder.build(filters)

            Log.d("QUERY_DEBUG", "Query final: '$query'")

            try {
                val response = RetrofitClient.api.searchCards(query)
                _cards.value = response.data
            } catch (e: Exception) {
                Log.e("API_ERROR", "Erro ao buscar cartas", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}