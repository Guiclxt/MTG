package com.example.mtg

import java.io.Serializable

data class ScryfallResponse(
    val data: List<MagicCard>
) : Serializable
