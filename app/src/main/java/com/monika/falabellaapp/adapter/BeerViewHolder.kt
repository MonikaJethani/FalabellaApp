package com.monika.falabellaapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.monika.falabellaapp.model.Beer
import kotlinx.android.synthetic.main.layout_beer.view.*

class BeerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(beer:Beer) = with(itemView) {
        itemView.beerName.text= beer.name
        itemView.beerAlcQuot.text = "Alcohol Content:"+beer.abv
        itemView.setTag(this@BeerViewHolder)
    }
}