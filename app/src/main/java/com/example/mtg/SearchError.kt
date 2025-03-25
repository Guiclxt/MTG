package com.example.mtg

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call

class SearchError(
    private val context: Context,
    private val imageView: ImageView,
    private val progressBar: ProgressBar
) {
    fun onFailure(call: Call<MagicCard>, t: Throwable) {
        progressBar.visibility = View.GONE
        showError("Erro ao buscar carta: $(t.message)")
    }

    fun showError(message: String) {
        imageView.visibility = View.GONE
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}