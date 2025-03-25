package com.example.mtg

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ScryfallApi {
    @GET("cards/named")
    fun getCard(@Query("fuzzy") cardName: String): Call<MagicCard>
}