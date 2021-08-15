package com.example.todolist_test

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter (private val todos: MutableList<Todo>)
    : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isChecked } // 람다 함수로 todos의 각 todo에 대해 isChecked를 확인
        notifyDataSetChanged()
    }

    private fun toggleStikeThrough(tView_todo_title: TextView, isChecked: Boolean) {
        if(isChecked) {
            tView_todo_title.paintFlags = tView_todo_title.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tView_todo_title.paintFlags = tView_todo_title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current_todo = todos[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tView_todo_title).text = current_todo.title     // 이런식으로 레이아웃의 아이디 찾아야 됨
            findViewById<CheckBox>(R.id.cbox_done).isChecked = current_todo.isChecked
            toggleStikeThrough(findViewById<TextView>(R.id.tView_todo_title), current_todo.isChecked)
            findViewById<CheckBox>(R.id.cbox_done).setOnCheckedChangeListener { _, isChecked ->
                toggleStikeThrough(findViewById<TextView>(R.id.tView_todo_title), isChecked)
                current_todo.isChecked = !current_todo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {  // 아이템의 개수 리턴해줌
        return todos.size
    }
}