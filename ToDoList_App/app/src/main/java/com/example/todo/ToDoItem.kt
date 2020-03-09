package com.example.todo

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ToDoItem : RealmObject() {
    @PrimaryKey
    private var id = UUID.randomUUID().toString()
    var name = ""
    var important = false

    fun getID() : String {
        return id
    }

    override fun toString() : String {
        return name
    }
}