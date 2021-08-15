package com.example.todolist_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter:TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        val recyclerView = findViewById<RecyclerView>(R.id.rView_todo_items)
        val addBtn = findViewById<Button>(R.id.btn_add_todo)
        val delBtn = findViewById<Button>(R.id.btn_del_done)
        val todoTitle = findViewById<EditText>(R.id.todo_title)

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addBtn.setOnClickListener {
            val todoTitle_str = todoTitle.text.toString()
            if(todoTitle_str.isNotEmpty()) {
                val todo = Todo(todoTitle_str)
                todoAdapter.addTodo(todo)
                todoTitle.text.clear()
            }
        }

        delBtn.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}