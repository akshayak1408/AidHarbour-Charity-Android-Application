package com.example.aidharbour

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var fullname: EditText
    private lateinit var username: EditText
    private lateinit var contactno: EditText
    private lateinit var password: EditText
    private lateinit var RegisterButton: Button
    private lateinit var LoginButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var referenceProfile: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fullname = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        contactno = findViewById(R.id.contactno)
        password = findViewById(R.id.password)
        RegisterButton = findViewById(R.id.RegButton)
        LoginButton = findViewById(R.id.button2)
        auth = FirebaseAuth.getInstance()
        referenceProfile = FirebaseDatabase.getInstance().getReference("RegisteredUsers")

        RegisterButton.setOnClickListener {
            val enteredFullname = fullname.text.toString()
            val enteredUsername = username.text.toString()
            val enteredContactno = contactno.text.toString()
            val enteredPassword = password.text.toString()

            if(TextUtils.isEmpty(enteredFullname) || TextUtils.isEmpty(enteredUsername) ||
                TextUtils.isEmpty(enteredContactno) || TextUtils.isEmpty(enteredPassword)) {
                showToast("Please fill in all fields.")
            } else {
                auth.createUserWithEmailAndPassword(enteredUsername, enteredPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val firebaseUser = auth.currentUser
                            firebaseUser?.let { user ->
                                val userDetails = HashMap<String, String>()
                                userDetails["userid"] = firebaseUser.uid
                                userDetails["fullname"] = enteredFullname
                                userDetails["username"] = enteredUsername
                                userDetails["contactno"] = enteredContactno
                                userDetails["password"] = enteredPassword

                                referenceProfile.child(user.uid).setValue(userDetails)
                                    .addOnCompleteListener { registrationTask ->
                                        if (registrationTask.isSuccessful) {
                                            showToast("User registered successfully!")

                                        } else {
                                            showToast("Failed to register user.")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        showToast("Failed to register user: ${exception.message}")
                                        Log.e("RegisterActivity", "Failed to register user.", exception)
                                    }
                            }
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
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}







