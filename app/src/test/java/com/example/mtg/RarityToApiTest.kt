package com.example.mtg

import org.junit.Test
import org.junit.Assert.assertEquals

class RarityToApiTest {

    @Test
    fun translateRarity_WhenRarityIsComum_ReturnCommon(){
        val actual = RarityToApi.translateRarity("comum")
        assertEquals("common", actual)
    }
}