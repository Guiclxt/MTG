package com.example.mtg.call.query.useCases

import com.example.mtg.call.translators.RarityToApi

class RarityQueryUseCase {
    operator fun invoke(rarity: String): String {
        if (rarity.isBlank()) return ""
        val rarityIn = RarityToApi.translateRarity(rarity)
        return "rarity:$rarityIn "
    }
}