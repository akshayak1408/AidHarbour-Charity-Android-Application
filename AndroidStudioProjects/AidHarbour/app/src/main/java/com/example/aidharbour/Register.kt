package com.example.aidharbour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var fullname: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var RegisterButton: Button
    private lateinit var LoginButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        fullname = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        RegisterButton = findViewById(R.id.RegButton)
        LoginButton = findViewById(R.id.button2)
        auth = FirebaseAuth.getInstance()
        RegisterButton.setOnClickListener {
            val enteredFullname = fullname.text.toString()
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()

            if(TextUtils.isEmpty(enteredFullname))
            {
                Toast.makeText(this, "Enter full name please!", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(enteredUsername))
            {
                Toast.makeText(this, "Enter username please!", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(enteredPassword))
            {
                Toast.makeText(this, "Enter password please!", Toast.LENGTH_SHORT).show();
            }
            else {
                auth.createUserWithEmailAndPassword(enteredUsername, enteredPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeFragment::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                baseContext,
                                "User already exists! Login to continue.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
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



