package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val retriver = weatherRetriver()

        val callback = object : Callback<weather>{
            override fun onFailure(call: Call<weather>, t: Throwable) {
            }

            override fun onResponse(call: Call<weather>?, response: Response<weather>?) {
                title = response?.body()?.city_name

                val forecasts = response?.body()?.data
                val forecastsString = mutableListOf<String>()
                if (forecasts !=null){
                    for (forecastem in forecasts){
                        val newString = "Temp: ${forecastem.temp}C || Cloudy ${forecastem.clouds}%"
                        forecastsString.add(newString)
                    }
                }

                val list = findViewById<ListView>(R.id.lista)
                val adapter = ArrayAdapter(this@ListActivity, android.R.layout.simple_list_item_1, forecastsString)
                list.adapter = adapter

            }
        }

        val searchTerm = intent.extras?.getString("searchTerm")

        if (searchTerm != null) {
            retriver.getWeather(callback, searchTerm)
        }
    }
}
