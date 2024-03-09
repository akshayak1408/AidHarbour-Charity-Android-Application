package com.example.aidharbour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var RegisterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        RegisterButton = findViewById(R.id.button2)

        loginButton.setOnClickListener {
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()
            /*if (enteredUsername == "ananya11@gmail.com" && enteredPassword == "ananya11@#") {
                showToast("Login Successful!")
                val intent = Intent(this, AboutFragment::class.java)
                startActivity(intent)
            } else {
                showToast("Login Failed!")
            }*/
        }

        RegisterButton.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
