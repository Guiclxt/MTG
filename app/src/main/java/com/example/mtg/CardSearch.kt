package com.example.mtg

import android.content.Context
import android.widget.ImageView
import android.widget.ProgressBar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardSearch {
    private val api: ScryfallApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.scryfall.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ScryfallApi::class.java)
    }

    fun searchCard(context: Context, cardName: String, imageView: ImageView, progressBar: ProgressBar) {
        progressBar.visibility = android.view.View.VISIBLE
        imageView.visibility = android.view.View.GONE

        val call = api.getCard(cardName)

        val cardVerifier = CardVerifier(context, imageView, progressBar)

        cardVerifier.verifyCard(call)
    }
}