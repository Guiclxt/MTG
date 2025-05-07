package com.example.mtg

object QueryBuilder {
    fun build(filters: CardFilters): String {
        return buildString {
            if (filters.name.isNotBlank()) {
                val name = if (filters.name.trim().contains(" "))
                    "\"${filters.name.trim()}\"" else filters.name.trim()
                append("name:$name ")
            }

            val setKey = SetToApi.translateSet(filters.set)
            if (setKey.isNotBlank()) {
                append("set:$setKey ")
            }

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

    }
}