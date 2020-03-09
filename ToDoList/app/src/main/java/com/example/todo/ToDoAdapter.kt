package com.example.todo

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ToDoAdapter(context: Context, resource: Int, objects: MutableList<ToDoItem>) : ArrayAdapter<ToDoItem>(context, resource, objects){
    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val toDoView = inflator.inflate(android.R.layout.simple_list_item_1, parent, false) as TextView

        val toDoItem= getItem(position)
        toDoView.text = toDoItem?.name
        if (toDoItem?.important == true){
            toDoView.setTypeface(Typeface.DEFAULT_BOLD)
        }
        return toDoView

    }
}



