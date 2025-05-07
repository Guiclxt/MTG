package com.example.mtg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CardsAdapter(private var cards: List<MagicCard>) :
    RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {

        fun refilterCards(newCards: List<MagicCard>) {
            cards = newCards
            notifyDataSetChanged()
        }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardImageView: ImageView = itemView.findViewById(R.id.cardImageView)

        fun bind(card: MagicCard) {
            Glide.with(itemView.context)
                .load(card.getImageUrl())
                .into(cardImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int = cards.size
}