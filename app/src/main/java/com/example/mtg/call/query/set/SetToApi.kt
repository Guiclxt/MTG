package com.example.mtg.call.query.set

object SetToApi {
    private val sets = mutableMapOf<String, String>()

    fun updateSets(apiSets: List<SetResponse>) {
        sets.clear()
        apiSets.forEach { sets[it.name.lowercase()] = it.code }
    }

    fun translateSet(setName: String): String {
        return sets[setName.lowercase()] ?: ""
    }

    fun getAllSetNames(): List<String> {
        return sets.keys.toList().sorted()
    }
}