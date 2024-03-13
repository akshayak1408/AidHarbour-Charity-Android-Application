package com.example.aidharbour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmine2.CustomAdapter

class Volunteers : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var regno: EditText
    lateinit var add: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteers)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.overflowIcon = null

        recyclerview.layoutManager = LinearLayoutManager(this)
        name = findViewById(R.id.name)
        regno = findViewById(R.id.rating)
        add = findViewById(R.id.add)

        val data = ArrayList<ItemsViewModel>()
        add.setOnClickListener {
            var nm = name.text.toString()
            var rn = regno.text.toString()
            var rn2 = rn.toInt()
            data.add(ItemsViewModel(nm,rn2))
            val adapter = CustomAdapter(data, this )
            recyclerview.adapter = adapter
        }

        val adapter = CustomAdapter(data, this)
        recyclerview.adapter = adapter


    }
}

