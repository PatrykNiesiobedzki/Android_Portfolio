package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val search = findViewById<Button>(R.id.search)
        search.setOnClickListener {
            val moveToSearch = Intent(getApplicationContext(), ListActivity::class.java)
            val searchEditText = findViewById<EditText>(R.id.searchText)
            moveToSearch.putExtra("searchTerm", searchEditText.text.toString())
            startActivity(moveToSearch)
        }
    }
}
