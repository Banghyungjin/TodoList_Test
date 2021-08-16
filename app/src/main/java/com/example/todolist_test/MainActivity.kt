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
        val recyclerView = findViewById<RecyclerView>(R.id.rView_todo_items)
        val addBtn = findViewById<Button>(R.id.btn_add_todo)
        val delBtn = findViewById<Button>(R.id.btn_del_done)
        val todoTitle = findViewById<EditText>(R.id.todo_title)

        todoAdapter = TodoAdapter(mutableListOf())
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        addBtn.setOnClickListener {
            val todoTitleStr = todoTitle.text.toString()
            if(todoTitleStr.isNotEmpty()) {
                val todo = Todo(todoTitleStr)
                todoAdapter.addTodo(todo)
                todoTitle.text.clear()
            }
        }

        delBtn.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}