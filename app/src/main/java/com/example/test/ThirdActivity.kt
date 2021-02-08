package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_third.*
import kotlinx.android.synthetic.main.item_todo.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        var todoList = mutableListOf(
            Todo("Testing RecyclerView",false),
            Todo("Membuat Aplikasi digital",false),
            Todo("Memberi makan Catty",true),
            Todo("Baca Novel baru",false),
            Todo("Testing RecyclerView",false),
            Todo("Membuat Aplikasi digital",false),
            Todo("Memberi makan Catty",true),
            Todo("Baca Novel baru",false)
        )
        val adapter = TodoAdapter(todoList)
        rvTodoList.adapter = adapter
        rvTodoList.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val title = etTodo.text.toString()
            val newTodo = Todo(title,false)
            todoList.add(newTodo)

            adapter.notifyItemInserted(todoList.size - 1)
        }
    }
}