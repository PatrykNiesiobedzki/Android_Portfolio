package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

import com.example.pokedex.Adapters.PokemonAdapter
import com.example.pokedex.Retrofit.list
import com.example.pokedex.Retrofit.listRetriver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class PokemonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        val pokemonListView = findViewById<ListView>(R.id.viewList)

        val retriver = listRetriver()

        val callback = object : Callback<list>{

            override fun onFailure(call: Call<list>, t: Throwable) {
            }
            override fun onResponse(call: Call<list>, response: Response<list>) {

                val pokemonList = response.body()?.pokemon as MutableList

                val NewAdapter = PokemonAdapter(this@PokemonListActivity, android.R.layout.simple_list_item_1, pokemonList)
                pokemonListView.adapter = NewAdapter

                pokemonListView.setOnItemClickListener { parent, view, position, id ->
                    val moveToDetails = Intent(this@PokemonListActivity, DetailsActivity::class.java)
                    val myPokemon = pokemonList.get(position)
                    moveToDetails.putExtra("myPokemon", myPokemon as Serializable)
                    startActivity(moveToDetails)
                }
            }
        }
        retriver.getList(callback)
    }
}
