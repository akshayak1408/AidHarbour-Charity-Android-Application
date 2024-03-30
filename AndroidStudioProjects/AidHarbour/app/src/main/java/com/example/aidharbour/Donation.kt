package com.example.aidharbour

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Donation : AppCompatActivity(){
    private lateinit var tv_title: TextView
    private lateinit var tv_address: EditText
    private lateinit var desc: Spinner
    private lateinit var volun: Spinner
    private lateinit var but_donate: Button
    private lateinit var view_but: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: DatabaseReference
    private lateinit var but_contact: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)
        tv_title = findViewById(R.id.text1_textview)
        tv_address = findViewById(R.id.address)
        desc = findViewById(R.id.description_spinner)
        volun = findViewById(R.id.volunteer_spinner)
        but_donate = findViewById(R.id.donate2)
        view_but = findViewById(R.id.viewhistory)
        but_contact = findViewById(R.id.contactvol)


        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        val title = intent.getStringExtra("marker_title")
        val uid = intent.getStringExtra("uid")

        tv_title.text = title

        view_but.setOnClickListener {
            val intent = Intent(this@Donation, History2::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)

        }

        but_contact.setOnClickListener {
            val intent = Intent(this@Donation, ContactVol::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)

        }
        but_donate.setOnClickListener {
            val address = tv_address.text.toString()
            val description = desc.selectedItem.toString()
            val volunteer = volun.selectedItem.toString()
            if (uid != null && title != null && address.isNotBlank()) {
                val userDonationsRef = mDatabase.child("donations").child(uid)

                val donationKey = userDonationsRef.push().key ?: ""

                val donationData = HashMap<String, Any>()
                donationData["uid"] = uid
                donationData["title"] = title
                donationData["address"] = address
                donationData["description"] = description
                donationData["volunteer"] = volunteer

                userDonationsRef.child(donationKey).setValue(donationData)
                    .addOnSuccessListener {
                       Toast.makeText(this@Donation, "Donation successful!",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@Donation, "Donation failed!",Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this@Donation, "NULL!",Toast.LENGTH_LONG).show()
            }
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.description_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            desc.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.volunteer_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            volun.adapter = adapter
        }
    }
}