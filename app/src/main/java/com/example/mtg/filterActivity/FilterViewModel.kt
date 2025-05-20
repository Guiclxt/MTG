package com.example.mtg.filterActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtg.call.RetrofitClient
import com.example.mtg.call.query.set.SetToApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilterViewModel: ViewModel() {

    private val _sets = MutableStateFlow<List<String>>(emptyList())
    val sets: StateFlow<List<String>> = _sets

    init {
        searchSets()
    }

    private fun searchSets() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getAllSets()
                val apiSets = response.data ?: emptyList()
                SetToApi.updateSets(apiSets)
                _sets.value = listOf("Sets") + SetToApi.getAllSetNames()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Erro ao buscar sets", e)
            }
        }
    }
}