package com.example.aidharbour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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
    private lateinit var but_update: Button
    private lateinit var googlemap: Button
    private lateinit var volunteers: Button
    private lateinit var viewhistory: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileafterlogin)
        val username = intent.getStringExtra("username")
        val uid = intent.getStringExtra("uid")
        tv_name = findViewById(R.id.textview_name)
        full_name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        cnum = findViewById(R.id.contact)
        pwd = findViewById(R.id.psswrd)
        databaseReference = FirebaseDatabase.getInstance().getReference("RegisteredUsers")
        but_update = findViewById(R.id.btn_update)
        googlemap = findViewById(R.id.btn_googlemaps)
        volunteers = findViewById(R.id.vol_rating)
        viewhistory = findViewById(R.id.view_history2)
        mAuth = FirebaseAuth.getInstance()

        volunteers.setOnClickListener {
            val intent = Intent(this, Volunteers::class.java)
            intent.putExtra("username", username)
            intent.putExtra("uid", uid)
            startActivity(intent)
        }

        viewhistory.setOnClickListener {
            val intent = Intent(this@Profilelogin, History2::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)

        }

        googlemap.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)
        }

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usersList = ArrayList<HashMap<String, String>>()

                for (userSnapshot in dataSnapshot.children) {
                    val userDataMap = userSnapshot.value as? HashMap<String, String>
                    if (userDataMap != null) {
                        usersList.add(userDataMap)
                    }
                }

                for (userDataMap in usersList) {
                    if (userDataMap["username"] == username) {
                        tv_name.text = userDataMap["fullname"]
                        full_name.setText(userDataMap["fullname"])
                        email.setText(userDataMap["username"])
                        cnum.setText(userDataMap["contactno"])
                        pwd.setText(userDataMap["password"])
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Database Error: ${databaseError.message}")
            }
        })

        but_update.setOnClickListener {
            val user = mAuth.currentUser
            val uid2 = user?.uid
            updateUserInfo(uid)
            Toast.makeText(this@Profilelogin, "Profile updated!",Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUserInfo(uid: String?) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val usersList = ArrayList<HashMap<String, String>>()

                for (userSnapshot in dataSnapshot.children) {
                    val userDataMap = userSnapshot.value as? HashMap<String, String>
                    if (userDataMap != null) {
                        usersList.add(userDataMap)
                    }
                }

                for (userDataMap in usersList) {
                    if (userDataMap["fullname"] == tv_name.text.toString()) {
                        userDataMap["username"] = email.text.toString()
                        userDataMap["contactno"] = cnum.text.toString()
                        userDataMap["password"] = pwd.text.toString()
                        if (uid != null) {
                            databaseReference.child(uid).setValue(userDataMap)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Database Error: ${databaseError.message}")
            }
        })
    }
}
