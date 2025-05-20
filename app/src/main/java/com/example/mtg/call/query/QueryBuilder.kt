package com.example.mtg.call.query

import com.example.mtg.call.query.useCases.ColorQueryUseCase
import com.example.mtg.call.query.useCases.MCostQueryUseCase
import com.example.mtg.call.query.useCases.NameQueryUseCase
import com.example.mtg.call.query.useCases.RarityQueryUseCase
import com.example.mtg.call.query.set.SetQueryUseCase
import com.example.mtg.call.query.useCases.TypeQueryUseCase
import com.example.mtg.filterActivity.CardFilters

class QueryBuilder(
    private val buildName: NameQueryUseCase,
    private val buildSet: SetQueryUseCase,
    private val buildColor: ColorQueryUseCase,
    private val buildRarity: RarityQueryUseCase,
    private val buildType: TypeQueryUseCase,
    private val buildMCost: MCostQueryUseCase
) {

    fun build(filters: CardFilters): String = buildList {
        buildName(filters.name).takeIf { it.contains(":") && it.substringAfter(":").isNotBlank() }?.let { add(it) }
        buildSet(filters.set).takeIf { it.contains(":") && it.substringAfter(":").isNotBlank() }?.let { add(it) }
        buildColor(filters.color).takeIf { it.contains(":") && it.substringAfter(":").isNotBlank() }?.let { add(it) }
        buildRarity(filters.rarity).takeIf { it.contains(":") && it.substringAfter(":").isNotBlank() }?.let { add(it) }
        buildType(filters.type).takeIf { it.contains(":") && it.substringAfter(":").isNotBlank() }?.let { add(it) }
        buildMCost(filters.manaCost).takeIf { it.contains(":") && it.substringAfter(":").isNotBlank() }?.let { add(it) }
    }.joinToString(" ")
}