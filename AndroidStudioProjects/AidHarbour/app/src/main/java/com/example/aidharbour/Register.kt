package com.example.aidharbour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    private lateinit var firstname: EditText
    private lateinit var lastname: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var RegisterButton: Button
    private lateinit var LoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        RegisterButton = findViewById(R.id.RegButton)
        LoginButton = findViewById(R.id.button2)

        RegisterButton.setOnClickListener {
            val enteredFirstname = firstname.text.toString()
            val enteredLastname = lastname.text.toString()
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()

            showToast("Registration Successful!")
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)

        }
        LoginButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}



