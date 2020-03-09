package com.example.todo

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = "ToDo List"

        fab.setOnClickListener {
            val moveIntent = Intent(this, AddToDoActivity::class.java)
            startActivity(moveIntent)
        }
    }

    override fun onResume() {
        var realm = Realm.getDefaultInstance()

        val query = realm.where(ToDoItem::class.java)
        val results = query.findAll()

        val listView = findViewById<ListView>(R.id.toDoListView)

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val selected = results[position]
            val finishIntent = Intent(this, FinishActivity::class.java)
            finishIntent.putExtra("toDoItem", selected?.getID())
            startActivity(finishIntent)
        }

        val newAdapter = ToDoAdapter(this, android.R.layout.simple_list_item_1, results)
        listView.adapter = newAdapter

        super.onResume()
    }
}
