package com.example.lab1_q2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
        val email: String="ak@gmail.com";
        val password: String ="1234";
        val mobile_number: String="7398282910";
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val email2 = findViewById(R.id.em) as EditText;
            val password2 = findViewById(R.id.password) as EditText;
            val mobileno = findViewById<EditText>(R.id.mbno)
            val loginbutton = findViewById(R.id.login) as Button;
            loginbutton.setOnClickListener {
                val userip = email2.text.toString();
                val passip = password2.text.toString();
                val mobile = mobileno.text.toString();
                if (isEmpty(userip) || isEmpty(passip) || isEmpty(mobile)) {
                    Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_LONG)
                        .show();
                }
                else{
                    if (email.compareTo(userip) == 0 && password.compareTo(passip) == 0 && mobile_number.compareTo(mobile) == 0) {
                        Toast.makeText(this@MainActivity, "Login successful!", Toast.LENGTH_LONG)
                            .show();
                    } else {
                        Toast.makeText(this@MainActivity, "Invalid credentials!", Toast.LENGTH_LONG)
                            .show();
                    }
                }

            }
        }
    }