package com.example.pokedex.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.Model.Evolution
import com.example.pokedex.R
import com.robertlevonyan.views.chip.Chip

class AdapterPokemonEvolution (var context: Context, var list: List<Evolution>) : RecyclerView.Adapter<AdapterPokemonEvolution.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.evolution_chip, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.text = list[position].name
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var chip = itemView.findViewById<Chip>(R.id.evolutionChip)
    }

}