package com.example.lab7_q1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btn_insert: Button
    private lateinit var btn_read: Button
    private lateinit var btn_update: Button
    private lateinit var btn_delete: Button
    private lateinit var regno: EditText
    private lateinit var name: EditText
    private lateinit var marks: EditText
    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_insert = findViewById(R.id.btnInsert)
        btn_read = findViewById(R.id.btnRead)
        btn_update = findViewById(R.id.btnUpdate)
        btn_delete = findViewById(R.id.btnDelete)
        regno = findViewById(R.id.etvRegno)
        name = findViewById(R.id.etvName)
        marks = findViewById(R.id.etvMarks)
        result = findViewById(R.id.tvResult)
        var db = DataBaseHandler(this)
        btn_insert.setOnClickListener {

            if(regno.text.toString().length > 0 && name.text.toString().length > 0 && marks.text.toString().length > 0)
            {
                val student = Student(regno.text.toString(), name.text.toString(), marks.text.toString())

                db.insertData(student)
            }
            else
            {
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }
        btn_read.setOnClickListener {
            var data = db.readData()
            result.text = ""
            for ( i in 0..data.size-1){
                result.append("\n" + data.get(i).id.toString()+ "\t" + data.get(i).regno + "\t" + data.get(i).name + "\t" + data.get(i).marks + "\n\n")
            }
        }
        btn_update.setOnClickListener{
            db.updateData()
            btn_read.performClick()
        }

        btn_delete.setOnClickListener {
            db.deleteData()
            btn_read.performClick()
        }


    }
}