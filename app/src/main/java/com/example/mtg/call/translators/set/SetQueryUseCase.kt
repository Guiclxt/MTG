package com.example.mtg.call.translators.set

import com.example.mtg.call.translators.SetToApi

class SetQueryUseCase {
    operator fun invoke(set: String): String {
        if (set.isBlank()) return ""
        val setIn = SetToApi.translateSet(set)
        return "set:$setIn "
    }
}