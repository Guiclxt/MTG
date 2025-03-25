package com.example.mtg

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardVerifier (
    private val context: Context,
    private val imageView: ImageView,
    private val progressBar: ProgressBar
) {

    fun verifyCard(call: Call<MagicCard>) {
        call.enqueue(object : Callback<MagicCard> {
            override fun onResponse(call: Call<MagicCard>, response: Response<MagicCard>) {
                progressBar.visibility = View.GONE

                if (response.isSuccessful && response.body() != null) {
                    val card = response.body()
                    val imageUrl = card?.getImageUrl()

                    if (!imageUrl.isNullOrEmpty()) {
                        imageView.visibility = View.VISIBLE
                        Glide.with(context)
                            .load(imageUrl)
                            .into(imageView)
                    } else {
                        SearchError(context, imageView, progressBar).showError("Imagem indisponível.")
                    }
                } else {
                    SearchError(context, imageView, progressBar).showError("Nome inválido.")
                }
            }

            override fun onFailure(call: Call<MagicCard>, t: Throwable) {
                SearchError(context, imageView, progressBar).onFailure(call, t)
            }
        })
    }
}