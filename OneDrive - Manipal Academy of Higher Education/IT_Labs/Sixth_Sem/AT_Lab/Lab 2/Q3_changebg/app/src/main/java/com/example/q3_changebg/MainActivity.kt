package com.example.q3_changebg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var blackk: Button
    lateinit var whitee: Button
    lateinit var purplee: Button
    lateinit var pinkk: Button
    lateinit var orangee: Button
    lateinit var bluee: Button
    lateinit var peachh: Button
    lateinit var main: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        blackk = findViewById(R.id.black)
        whitee = findViewById(R.id.white)
        purplee = findViewById(R.id.purple)
        pinkk = findViewById(R.id.pink)
        orangee = findViewById(R.id.orange)
        bluee = findViewById(R.id.blue)
        peachh = findViewById(R.id.peach)
        main = findViewById(R.id.main_layout)

        blackk.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        }
        whitee.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            //main.setBackgroundColor(resources.getColor(R.color.white)) --> deprecated
        }
        purplee.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))

        }
        pinkk.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.pink))
        }
        orangee.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
        }
        bluee.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
        }
        peachh.setOnClickListener {
            main.setBackgroundColor(ContextCompat.getColor(this, R.color.peach))
        }
    }
}