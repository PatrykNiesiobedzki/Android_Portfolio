package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.Adapters.AdapterPokemonEvolution
import com.example.pokedex.Adapters.AdapterPokemonType
import com.example.pokedex.Model.Evolution
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Other.loadUrl

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val myPokemon = intent.getSerializableExtra("myPokemon") as Pokemon

        val imgUlr = findViewById<ImageView>(R.id.detailImage)
        val name = findViewById<TextView>(R.id.detailName)
        val weight = findViewById<TextView>(R.id.detailWeight)
        val height = findViewById<TextView>(R.id.detailHeight)
        val typeList = findViewById<RecyclerView>(R.id.typeRecycler)
        val nextList = findViewById<RecyclerView>(R.id.nextRecycler)
        val prevList = findViewById<RecyclerView>(R.id.prevRecycler)

        val weaknessesList = findViewById<RecyclerView>(R.id.weaknessesRecycler)
        weaknessesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //you can set this in xml file

        imgUlr.loadUrl(myPokemon.img)
        name.text = myPokemon.name
        weight.text = ("Weight: " + myPokemon.weight)
        height.text = ("Height: " + myPokemon.height)

        val typeAdapter = AdapterPokemonType(this, myPokemon.type!!)
        typeList.adapter = typeAdapter

        val weaknessesAdapter = AdapterPokemonType(this, myPokemon.weaknesses!!)
        weaknessesList.adapter = weaknessesAdapter

        if (myPokemon.next_evolution !=null) {
            val nextAdapter = AdapterPokemonEvolution(this, myPokemon.next_evolution!!)
            nextList.adapter = nextAdapter
        }

        if (myPokemon.prev_evolution !=null) {
            val prevAdapter = AdapterPokemonEvolution(this, myPokemon.prev_evolution!!)
            prevList.adapter = prevAdapter
        }
    }
}