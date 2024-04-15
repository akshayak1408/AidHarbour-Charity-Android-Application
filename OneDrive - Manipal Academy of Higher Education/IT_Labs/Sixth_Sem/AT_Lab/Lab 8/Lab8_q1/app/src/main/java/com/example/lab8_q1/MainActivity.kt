package com.example.lab8_q1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btn_insert: Button
    private lateinit var btn_read: Button
    private lateinit var btn_number: Button
    private lateinit var btn_max: Button
    private lateinit var btn_min: Button
    private lateinit var name: EditText
    private lateinit var cost: EditText
    private lateinit var result: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_insert = findViewById(R.id.btnInsert)
        btn_read = findViewById(R.id.btnRead)
        btn_number = findViewById(R.id.btnNum)
        btn_max = findViewById(R.id.btnMax)
        btn_min = findViewById(R.id.btnMin)
        name = findViewById(R.id.etvName)
        cost = findViewById(R.id.etvCost)
        result = findViewById(R.id.tvResult)
        var db = DataBaseHandler(this)
        btn_insert.setOnClickListener {

            if (name.text.toString().isNotEmpty() && cost.text.toString().isNotEmpty()) {
                val product = Product(name.text.toString(), cost.text.toString().toDouble())

                db.insertData(product)
                //Toast.makeText(this, "Inserted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }

        btn_read.setOnClickListener {
            var data = db.readData()
            result.text = ""
            for (i in 0 until data.size) {
                result.append(data[i].id.toString() + " " + data[i].name + " " + data[i].cost.toString() + "\n\n")
            }
        }

        btn_number.setOnClickListener {
            val count = db.getTotalNumberOfProducts()
            result.text = "Count: $count"
            Toast.makeText(this, "Num success!", Toast.LENGTH_SHORT).show()
        }

        btn_max.setOnClickListener {
            val prod = db.getMaxPricedProduct()
            if (prod != null) {
                result.text = "Max \nName: ${prod.name} \t ${prod.cost}"
                Toast.makeText(this, "Max success!", Toast.LENGTH_SHORT).show()
            }
        }

        btn_min.setOnClickListener {
            val prod = db.getMinPricedProduct()
            if (prod != null) {
                result.text = "Min \nName: ${prod.name} \t ${prod.cost}"
                Toast.makeText(this, "Min success!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
