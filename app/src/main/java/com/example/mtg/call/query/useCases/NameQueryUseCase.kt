package com.example.mtg.call.query.useCases

class NameQueryUseCase {
    operator fun invoke(name: String): String {
        if (name.isBlank()) return ""
        val nameIn = if (name.trim().contains(" ")) "\"${name.trim()}\"" else name.trim()
        return "name:$nameIn "
    }
}