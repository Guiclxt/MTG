package com.example.mtg.call.translators

object TypeToApi {
    fun translateType (type: String): String {
        return when (type.lowercase()) {
            "mágica instantânea" -> "instant"
            "feitiço" -> "sorcery"
            "artefato" -> "artifact"
            "criatura" -> "creature"
            "encantamento" -> "anchantment"
            "terreno" -> "land"
            "planinauta" -> "planeswalker"
            "batalha" -> "battle"
            else -> ""
        }
    }
}