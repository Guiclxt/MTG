package com.example.mtg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_filter)

        //Variáveis do layout:
        val cardNameEditText = findViewById<EditText>(R.id.cardNameEditText)
        val cardSetEditText = findViewById<EditText>(R.id.cardSetEditText)
        val manaCostEditText = findViewById<EditText>(R.id.manaCostEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val cardColorSpinner = findViewById<Spinner>(R.id.cardColorSpinner)
        val cardRaritySpinner = findViewById<Spinner>(R.id.cardRaritySpinner)
        val cardTypeSpinner = findViewById<Spinner>(R.id.cardTypeSpinner)

        val colorOptions = arrayOf("Cores", "Branco", "Preto", "Verde", "Vermelho", "Azul", "Sem cor")
        val raritiesOptions = arrayOf("Raridade", "Terreno Básico", "Comum", "Incomum", "Rara", "Rara Mítica", "Especial")
        val typeOptions = arrayOf("Tipo", "Mágica Instantânea", "Feitiço", "Artefato", "Criatura", "Encantamento", "Terreno", "Planinauta", "Batalha")

        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("CARD_NAME", cardNameEditText.text.toString())
            intent.putExtra("CARD_SET", cardNameEditText.text.toString())
            intent.putExtra("CARD_COLOR", cardNameEditText.text.toString())
            intent.putExtra("CARD_RARITY", cardNameEditText.text.toString())
            intent.putExtra("CARD_TYPE", cardNameEditText.text.toString())
            intent.putExtra("MANA_COST", cardNameEditText.text.toString())
            startActivity(intent)

        }
    }
}