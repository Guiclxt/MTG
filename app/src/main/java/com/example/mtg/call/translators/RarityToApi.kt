package com.example.mtg.call.translators

object RarityToApi {
    fun translateRarity(rarity: String): String {
        return when (rarity.lowercase()) {
            "comum" -> "common"
            "incomum" -> "uncommon"
            "rara" -> "rare"
            "rara mítica" -> "mythic"
            "especial" -> "special"
            else -> ""
        }
    }
}