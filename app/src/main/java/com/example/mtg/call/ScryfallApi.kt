package com.example.mtg.call

import com.example.mtg.call.query.set.SetListSearched
import retrofit2.http.GET
import retrofit2.http.Query

interface ScryfallApi {
    @GET("cards/search")
    suspend fun searchCards(
        @Query("q") query: String
    ): ScryfallResponse

    @GET("sets")
    suspend fun getAllSets(): SetListSearched
}