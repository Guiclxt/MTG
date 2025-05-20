package com.example.mtg.call

import java.io.Serializable

data class ScryfallResponse(
    val data: List<MagicCard>
) : Serializable
