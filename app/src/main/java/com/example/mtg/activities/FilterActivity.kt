package com.example.mtg.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mtg.CardFilters
import com.example.mtg.Constants
import com.example.mtg.FilterViewModel
import com.example.mtg.R
import kotlinx.coroutines.launch

class FilterActivity : AppCompatActivity() {

    private val filterViewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        //Variáveis do layout:
        val cardNameEditText = findViewById<EditText>(R.id.cardNameEditText)
        val manaCostEditText = findViewById<EditText>(R.id.manaCostEditText)

        val cardSetSpinner = findViewById<Spinner>(R.id.cardSetSpinner)
        val cardColorSpinner = findViewById<Spinner>(R.id.cardColorSpinner)
        val cardRaritySpinner = findViewById<Spinner>(R.id.cardRaritySpinner)
        val cardTypeSpinner = findViewById<Spinner>(R.id.cardTypeSpinner)

        val colorOptions = arrayOf("Cores", "Branco", "Preto", "Verde", "Vermelho", "Azul", "Sem cor")
        val raritiesOptions = arrayOf("Raridade", "Comum", "Incomum", "Rara", "Rara Mítica", "Especial")
        val typeOptions = arrayOf("Tipo", "Mágica Instantânea", "Feitiço", "Artefato", "Criatura", "Encantamento", "Terreno", "Planinauta", "Batalha")

        val searchButton = findViewById<Button>(R.id.searchButton)


        //Colocando os arrays dentro dos spinners pra aparecerem as opções na tela
        cardColorSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorOptions)
        cardRaritySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, raritiesOptions)
        cardTypeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, typeOptions)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                filterViewModel.sets.collect { setNames ->
                    val adapter = ArrayAdapter(this@FilterActivity, android.R.layout.simple_spinner_item, setNames)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    cardSetSpinner.adapter = adapter
                }
            }
        }

        searchButton.setOnClickListener {
            val filters = CardFilters(
                name = cardNameEditText.text.toString(),
                set = cardSetSpinner.selectedItem?.toString() ?: "",
                color = cardColorSpinner.selectedItem.toString(),
                rarity = cardRaritySpinner.selectedItem.toString(),
                type = cardTypeSpinner.selectedItem.toString(),
                manaCost = manaCostEditText.text.toString()
            )

            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra(Constants.FILTER_KEY, filters)
            startActivity(intent)
            finish()
        }
    }
}