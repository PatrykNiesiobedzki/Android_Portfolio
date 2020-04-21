package com.example.pokedex.Retrofit

import com.example.pokedex.Model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonAPI {
    @GET("Biuni/PokemonGO-Pokedex/master/pokedex.json")
    fun getList() : Call<list>
}
class  list{
    var pokemon: List<Pokemon>? = null
}

class listRetriver {
    private val service : PokemonAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(PokemonAPI::class.java)
    }

    fun getList(callback: Callback<list>){
        val call = service.getList()
        call.enqueue(callback)
    }
}
