package com.example.pokedex.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.Other.SetElementColor
import com.example.pokedex.R
import com.robertlevonyan.views.chip.Chip

class AdapterPokemonType(var context : Context, var list : List<String>) : RecyclerView.Adapter<AdapterPokemonType.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.type_chip, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.chip.text = list[position]
        holder.chip.chipBackgroundColor = (SetElementColor.getColorByType(list[position]))
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var chip = itemView.findViewById<Chip>(R.id.evolutionChip)
    }
}
