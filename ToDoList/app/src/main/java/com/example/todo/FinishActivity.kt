package com.example.todo

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Button
import android.widget.TextView


class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        title = "Finish Task"

        val toDoItemId = intent.getStringExtra("toDoItem")
        var realm = Realm.getDefaultInstance()
        val toDoItem = realm.where(ToDoItem::class.java).equalTo("id", toDoItemId).findFirst()

        val textView = findViewById<TextView>(R.id.completeTextView)
        textView.text = toDoItem?.name

        if (toDoItem?.important == true){
            textView.setTypeface(Typeface.DEFAULT_BOLD)
        }

        val finishButton = findViewById<Button>(R.id.completeButton)
        finishButton.setOnClickListener {
            realm.beginTransaction()
            toDoItem?.deleteFromRealm()
            realm.commitTransaction()

            finish()
        }
    }
}
