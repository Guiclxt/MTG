package com.example.mtg.call.query.set

class SetQueryUseCase {
    operator fun invoke(set: String): String {
        if (set.isBlank()) return ""
        val setIn = SetToApi.translateSet(set)
        return "set:$setIn "
    }
}