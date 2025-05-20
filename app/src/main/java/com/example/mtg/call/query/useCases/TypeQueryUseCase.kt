package com.example.mtg.call.query.useCases

import com.example.mtg.call.translators.TypeToApi

class TypeQueryUseCase {
    operator fun invoke(type: String): String {
        if (type.isBlank()) return ""
        val typeIn = TypeToApi.translateType(type)
        return "type:$typeIn "
    }
}