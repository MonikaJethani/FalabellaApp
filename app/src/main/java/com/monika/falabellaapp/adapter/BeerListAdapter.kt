package com.monika.falabellaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monika.falabellaapp.R
import com.monika.falabellaapp.model.Beer

class BeerListAdapter (val beers:List<Beer>) : RecyclerView.Adapter<BeerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_beer, parent, false))
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beers.get(position))
    }

    fun getBeers(position: Int) : Beer {
        return beers[position]
    }
}