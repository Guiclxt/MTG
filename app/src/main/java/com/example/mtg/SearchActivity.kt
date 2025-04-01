package com.example.mtg

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val resultsTextView = findViewById<TextView>(R.id.searchTextView)

        val cardName = intent.getStringExtra("CARD_NAME") ?: ""
        val cardSet = intent.getStringExtra("CARD_SET") ?: ""
        val cardColor = intent.getStringExtra("CARD_COLOR") ?: ""
        val cardRarity = intent.getStringExtra("CARD_RARITY") ?: ""
        val cardType = intent.getStringExtra("CARD_Type") ?: ""
        val manaCost = intent.getStringExtra("MANA_COST") ?: ""

        //Teste:
        val resultText = "Nome: $cardName\\nSet: $cardSet\\nCor: $cardColor\\nRaridade: $cardRarity\\nTipo: $cardType\\nCusto de Mana: $manaCost"
        resultsTextView.text = resultText

        cardsRecyclerView.layoutManager = LinearLayoutManager(this)
        cardsRecyclerView.adapter = CardsAdapter(emptyList())


    }
}