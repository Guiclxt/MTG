package com.example.mtg.searchActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtg.filterActivity.CardFilters
import com.example.mtg.CardsAdapter
import com.example.mtg.Constants
import com.example.mtg.R
import com.example.mtg.filterActivity.FilterActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

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

        val loadingCircle = findViewById<ProgressBar>(R.id.loading_circle)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    loadingCircle.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }

        refilterButton.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}