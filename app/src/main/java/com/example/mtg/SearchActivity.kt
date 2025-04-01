package com.example.mtg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val cardsRecyclerView = findViewById<RecyclerView>(R.id.)
        val refilterButton = findViewById<Button>(R.id.)

        val cardName = intent.getStringExtra("CARD_NAME") ?: ""
        val cardSet = intent.getStringExtra("CARD_SET") ?: ""
        val cardColor = intent.getStringExtra("CARD_COLOR") ?: ""
        val cardRarity = intent.getStringExtra("CARD_RARITY") ?: ""
        val cardType = intent.getStringExtra("CARD_Type") ?: ""
        val manaCost = intent.getStringExtra("MANA_COST") ?: ""


        //Configuração do RecyclerView (Tem muito chão pela frente)
        cardsRecyclerView.layoutManager = LinearLayoutManager(this)
        cardsRecyclerView.adapter = CardsAdapter(emptyList())

        //Botão que volta pra tela inicial
        refilterButton.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}