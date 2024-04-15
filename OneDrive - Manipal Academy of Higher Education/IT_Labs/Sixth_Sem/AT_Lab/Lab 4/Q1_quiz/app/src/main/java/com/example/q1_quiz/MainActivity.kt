package com.example.q1_quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var q1: EditText
        lateinit var q2: EditText
        lateinit var q3: EditText
        lateinit var q4: EditText
        lateinit var q5: EditText
        lateinit var but: Button
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        q1 = findViewById(R.id.editTextText2)
        q2 = findViewById(R.id.editTextText)
        q3 = findViewById(R.id.editTextText1)
        q4 = findViewById(R.id.editTextText4)
        q5 = findViewById(R.id.editTextText3)
        but = findViewById(R.id.button)

        but.setOnClickListener {
            val ans1 = q1.text.toString()
            val ans2 = q2.text.toString()
            val ans3 = q3.text.toString()
            val ans4 = q4.text.toString()
            val ans5 = q5.text.toString()

            val artDialogBuilder = AlertDialog.Builder(this@MainActivity)

            artDialogBuilder.setMessage("Are you sure you want to submit?")
            artDialogBuilder.setTitle("Submission")
            artDialogBuilder.setCancelable(false)
            artDialogBuilder.setPositiveButton("Yes") { _,_ ->
                val intent = Intent(this,MainActivity2::class.java) // we created an intent object and
                // then call it in startActivity to redirect it to second activity)
                intent.putExtra("ANS1", ans1)
                intent.putExtra("ANS2", ans2)
                intent.putExtra("ANS3", ans3)
                intent.putExtra("ANS4", ans4)
                intent.putExtra("ANS5", ans5)
                startActivity(intent)
            }

            artDialogBuilder.setNegativeButton("No"){_,_ ->
               Toast.makeText(this@MainActivity, "Clicked no", Toast.LENGTH_SHORT).show()
            }

            artDialogBuilder.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(this@MainActivity, "Clicked cancel", Toast.LENGTH_SHORT).show()
            }

            val alertDialogBox = artDialogBuilder.create()

            alertDialogBox.show()

        }
    }
}