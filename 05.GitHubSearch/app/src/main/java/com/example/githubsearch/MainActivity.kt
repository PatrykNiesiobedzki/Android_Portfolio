package com.example.githubsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val search = findViewById<Button>(R.id.searchButton)
        search.setOnClickListener {
            val moveToSearch = Intent(getApplicationContext(), ListActivity::class.java)
            val searchText = findViewById<EditText>(R.id.searchText)
            moveToSearch.putExtra("searchParameter", searchText.text.toString())
            startActivity(moveToSearch)
        }
    }
}
