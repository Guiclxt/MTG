package com.example.mtg

object ColorToApi {
    fun translateColor(color: String): String {
        return when (color.lowercase()) {
            "azul" -> "u"
            "preto" -> "b"
            "verde" -> "g"
            "vermelho" -> "r"
            "branco" -> "w"
            else -> ""
        }
    }
}