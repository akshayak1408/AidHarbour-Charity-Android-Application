package com.example.aidharbour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var RegisterButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        RegisterButton = findViewById(R.id.button2)
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            val enteredUsername = username.text.toString()
            val enteredPassword = password.text.toString()

            if(TextUtils.isEmpty(enteredUsername))
            {
                Toast.makeText(this, "Enter username please!", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(enteredPassword))
            {
                Toast.makeText(this, "Enter password please!", Toast.LENGTH_SHORT).show();
            }
            else {
                auth.signInWithEmailAndPassword(enteredUsername, enteredPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            showToast("Login Successful! ")
                            val user = auth.currentUser
                            val uid = user?.uid
                            val intent = Intent(this, Profilelogin::class.java)
                            intent.putExtra("username", enteredUsername)
                            intent.putExtra("uid", uid)
                            startActivity(intent)
                        } else {
                            showToast("Login Failed!")
                        }
                    }
            }
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