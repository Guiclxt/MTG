package com.example.mtg

import com.example.mtg.call.translators.RarityToApi
import org.junit.Test
import org.junit.Assert.assertEquals

class RarityToApiTest {

    @Test
    fun translateRarity_WhenRarityIsComum_ReturnCommon(){
        val actual = RarityToApi.translateRarity("comum")
        assertEquals("common", actual)
    }
}