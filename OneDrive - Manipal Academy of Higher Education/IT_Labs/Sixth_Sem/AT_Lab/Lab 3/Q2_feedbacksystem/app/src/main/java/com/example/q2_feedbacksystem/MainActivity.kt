package com.example.q2_feedbacksystem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var but: Button
    lateinit var main: RadioGroup
    lateinit var res: TextView
    lateinit var add: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        but = findViewById(R.id.button1)
        main = findViewById(R.id.idRadioGroup)
        res = findViewById(R.id.result)
        add = findViewById(R.id.additional)

        main.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            res.text = "You selected ${radioButton.text}"
        }

        but.setOnClickListener {
            val txt = add.text.toString()
            if(txt == "")
            {
                Toast.makeText(this, "Please provide additional feedback!", Toast.LENGTH_LONG).show()
            }
        }
    }
}