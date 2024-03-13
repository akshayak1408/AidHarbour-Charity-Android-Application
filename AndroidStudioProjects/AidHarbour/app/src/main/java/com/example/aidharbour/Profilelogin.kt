package com.example.aidharbour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Profilelogin : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var tv_name: TextView
    private lateinit var full_name: com.google.android.material.textfield.TextInputEditText
    private lateinit var email: com.google.android.material.textfield.TextInputEditText
    private lateinit var cnum: com.google.android.material.textfield.TextInputEditText
    private lateinit var pwd: com.google.android.material.textfield.TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileafterlogin)
        val username = intent.getStringExtra("username")
        tv_name = findViewById(R.id.textview_name)
        full_name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        cnum = findViewById(R.id.contact)
        pwd = findViewById(R.id.psswrd)
        databaseReference = FirebaseDatabase.getInstance().getReference("RegisteredUsers")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val userDataMap = userSnapshot.value as? Map<*, *> ?: continue

                    val usernameFromDB = userDataMap["username"] as? String
                    if (usernameFromDB == username) {
                        val fullName = userDataMap["fullname"] as? String
                        val userEmail = userDataMap["username"] as? String
                        val contactNo = userDataMap["contactno"] as? String
                        val password = userDataMap["password"] as? String

                        tv_name.text = fullName
                        full_name.setText(fullName)
                        email.setText(userEmail)
                        cnum.setText(contactNo)
                        pwd.setText(password)

                        return
                    }
                }
            }


            override fun onCancelled(databaseError: DatabaseError) {
                println("Database Error: ${databaseError.message}")
            }
        })

    }
}