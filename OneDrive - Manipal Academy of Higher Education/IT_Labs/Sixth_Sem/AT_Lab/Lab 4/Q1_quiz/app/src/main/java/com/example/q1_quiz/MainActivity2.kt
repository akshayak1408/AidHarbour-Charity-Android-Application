package com.example.q1_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var result: TextView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        result = findViewById(R.id.res)

        val a1 = intent.getStringExtra("ANS1")
        val a2 = intent.getStringExtra("ANS2")
        val a3 = intent.getStringExtra("ANS3")
        val a4 = intent.getStringExtra("ANS4")
        val a5 = intent.getStringExtra("ANS5")

        var resno = 0
        val res2 = StringBuilder()
        if(a1 == ""  || a1!="Paris")
        {
            res2.append("1: Incorrect!\n")

        }
        else{
            res2.append("1: Correct!\n")
            resno++
        }
        if(a2 == ""  || a2!="Jupiter")
        {
            res2.append("2: Incorrect!\n")
        }
        else{
            res2.append("2: Correct!\n")
            resno++
        }
        if(a3 == ""  || a3!="Photosynthesis")
        {
            res2.append("3: Incorrect!\n")
        }
        else{
            res2.append("3: Correct!\n")
            resno++
        }
        if(a4 == ""  || a4!="Albert Einstein")
        {
            res2.append("4: Incorrect!\n")
        }
        else{
            res2.append("4: Correct!\n")
            resno++
        }
        if(a5 == ""  || a5!="H2O")
        {
            res2.append("5: Incorrect!\n")
        }
        else{
            res2.append("5: Correct!\n")
            resno++
        }

        result.text = res2.toString() + "\nTotal score: ${resno}/5"
    }
}