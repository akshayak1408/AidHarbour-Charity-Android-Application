package com.example.q2_encrypt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var but: Button
    lateinit var en: EditText
    lateinit var de: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        but = findViewById(R.id.button)
        en = findViewById(R.id.encrypt)
        de = findViewById(R.id.decrypt)

        but.setOnClickListener {
            val decry = de.text.toString()
            var encry = StringBuilder()
            if( decry == "")
            {
                Toast.makeText(this, "Please enter the string to encrypt", Toast.LENGTH_LONG).show()
            }
            else
            {
                for(i in decry)
                {
                    encry.append(i + 1);
                }
                var result = encry.toString()
                en.setText(result)
            }
        }
    }
}