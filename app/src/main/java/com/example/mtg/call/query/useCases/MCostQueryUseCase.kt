package com.example.mtg.call.query.useCases

class MCostQueryUseCase {
    operator fun invoke(mCost: String): String {
        if (mCost.isBlank()) return ""
        return "cmc:${mCost.trim()}"
    }
}