package com.example.mtg.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtg.CardFilters
import com.example.mtg.CardsAdapter
import com.example.mtg.Constants
import com.example.mtg.R
import com.example.mtg.RetrofitClient
import com.example.mtg.SearchViewModel
import com.example.mtg.activities.FilterActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    private lateinit var cardsRecyclerView: RecyclerView
    private lateinit var refilterButton: Button
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val cardsRecyclerView = findViewById<RecyclerView>(R.id.cardsRecyclerView)
        val refilterButton = findViewById<Button>(R.id.refilterButton)


        cardsRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CardsAdapter(emptyList())
        cardsRecyclerView.adapter = adapter


        val filters = intent.getSerializableExtra(Constants.FILTER_KEY) as? CardFilters
        filters?.let { viewModel.findCards(it) }


        lifecycleScope.launch {
            viewModel.cards.collectLatest { cards ->
                adapter.refilterCards(cards)
            }
        }


        refilterButton.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}