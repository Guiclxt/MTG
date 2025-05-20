package com.example.mtg.filterActivity

import java.io.Serializable

data class CardFilters(
    val name: String,
    val set: String,
    val color: String,
    val rarity: String,
    val type: String,
    val manaCost: String
) : Serializable