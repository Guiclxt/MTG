package com.example.mtg

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val cardSearch = CardSearch()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val cardNameEditText = findViewById<EditText>(R.id.cardNameEditText)
        val button = findViewById<Button>(R.id.searchButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val cardImage = findViewById<ImageView>(R.id.cardImageView)

        button.setOnClickListener {
            val cardName = cardNameEditText.text.toString().trim()

            if(cardName.isEmpty()) {
                Toast.makeText(this, "Digite o nome da carta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE
            cardImage.visibility = View.GONE

            cardSearch.searchCard(this, cardName, cardImage, progressBar)
        }
    }
}