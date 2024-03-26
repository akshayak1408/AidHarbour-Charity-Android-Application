package com.example.aidharbour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmine2.CustomAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Volunteers : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var regno: EditText
    lateinit var add: Button
    lateinit var reference: DatabaseReference
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
        reference = FirebaseDatabase.getInstance().getReference("Volunteers")
        val id = intent.getStringExtra("uid")
        val usrnm = intent.getStringExtra("username")
        val data = ArrayList<ItemsViewModel>()

        add.setOnClickListener {
            val nm = name.text.toString()
            val rn = regno.text.toString()
            val vid = id.toString()
            val username = usrnm.toString()
            val entryData = HashMap<String, Any>()
            entryData["rated_by"] = username
            entryData["v_name"] = nm
            entryData["v_rating"] = rn

            val entryKey = reference.child(vid).push().key ?: ""

            reference.child(vid).child(entryKey).setValue(entryData)
                .addOnSuccessListener {
                    val rn2 = rn.toDouble()
                    data.add(ItemsViewModel(nm, rn2))
                    val adapter = CustomAdapter(data, this)
                    recyclerview.adapter = adapter
                }

        }

        val adapter = CustomAdapter(data, this)
        recyclerview.adapter = adapter

    }
}

