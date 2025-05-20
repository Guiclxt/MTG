package com.example.mtg.call.query.useCases

import com.example.mtg.call.translators.ColorToApi

class ColorQueryUseCase {
    operator fun invoke(color: String): String {
        if (color.isBlank()) return ""
        val colorIn = ColorToApi.translateColor(color)
        return "color:$colorIn "
    }
}