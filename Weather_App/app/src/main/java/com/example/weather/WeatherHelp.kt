package com.example.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherAPI {
    @GET("v2.0/forecast/hourly?&key=b921a5254a2040bab4d404182a2c1075&hours=24")
    fun getWeather(@Query("city") city: String) : Call<weather>
}
class weather(val city_name : String, val data: List<forecasts>)
class forecasts(val temp: String, val clouds: String, val timestamp_local: String)

class weatherRetriver {
    val service: weatherAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.weatherbit.io/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(weatherAPI::class.java)
    }

    fun getWeather(callback: Callback<weather>, searchTerm: String){
        val city = searchTerm
        val call =service.getWeather(city)
        call.enqueue(callback)
    }
}