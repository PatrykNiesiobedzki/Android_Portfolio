package com.example.pokedex.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Other.loadUrl
import com.example.pokedex.R

class PokemonAdapter(context: Context, resource: Int, objects: MutableList<Pokemon>) : ArrayAdapter<Pokemon>(context, resource, objects){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val pokemonView = inflator.inflate(R.layout.pokemon_list, parent, false)

        val textView = pokemonView.findViewById<TextView>(R.id.pokemonName)
        val imageView = pokemonView.findViewById<ImageView>(R.id.pokemonImageView)

        val listResults = getItem(position)
        textView.text = listResults?.name
        imageView.loadUrl(listResults?.img)
        return pokemonView
    }
}