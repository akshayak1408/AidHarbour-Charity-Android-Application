package com.example.lab2_q1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.slider.BasicLabelFormatter

class MainActivity : AppCompatActivity() {
    lateinit var apples: CheckBox
    lateinit var milk: CheckBox
    lateinit var juice: CheckBox
    lateinit var bourbon: CheckBox
    lateinit var beans: CheckBox
    lateinit var riceflour: CheckBox
    lateinit var icecream: CheckBox
    lateinit var cashews: CheckBox
    lateinit var mangoes: CheckBox
    lateinit var chocolates: CheckBox
    lateinit var litchi: CheckBox
    lateinit var basil: CheckBox
    lateinit var berry: CheckBox
    lateinit var lemon: CheckBox
    lateinit var pecan: CheckBox
    lateinit var submit: Button
    lateinit var res2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apples=findViewById(R.id.checkBox1)
        beans = findViewById(R.id.checkBox2)
        milk = findViewById(R.id.checkBox3)
        juice = findViewById(R.id.checkBox4)
        bourbon = findViewById(R.id.checkBox5)
        riceflour = findViewById(R.id.checkBox6)
        icecream = findViewById(R.id.checkBox7)
        cashews = findViewById(R.id.checkBox8)
        mangoes = findViewById(R.id.checkBox9)
        chocolates = findViewById(R.id.checkBox10)
        litchi = findViewById(R.id.checkBox11)
        basil = findViewById(R.id.checkBox12)
        berry = findViewById(R.id.checkBox13)
        lemon = findViewById(R.id.checkBox14)
        pecan = findViewById(R.id.checkBox15)
        submit = findViewById(R.id.button)
        res2 = findViewById(R.id.textView2)
        submit.setOnClickListener {
            val res = StringBuilder()
            res.append("")
            if (apples.isChecked){
                res.append("Apples\n");
            }
            if (beans.isChecked){
                res.append(" Beans\n");
            }
            if (milk.isChecked){
                res.append(" Milk\n");
            }
            if (juice.isChecked){
                res.append(" Juice\n");
            }
            if (bourbon.isChecked){
                res.append(" Bourbon\n");
            }
            if (riceflour.isChecked){
                res.append(" RiceFlour\n");
            }
            if (icecream.isChecked){
                res.append(" IceCream\n");
            }
            if (cashews.isChecked){
                res.append(" Cashews\n");
            }
            if (mangoes.isChecked){
                res.append(" Mangoes\n");
            }
            if (chocolates.isChecked){
                res.append(" Chocolates\n");
            }
            if (litchi.isChecked){
                res.append(" Litchi\n");
            }
            if (basil.isChecked){
                res.append(" Basil\n");
            }
            if (berry.isChecked){
                res.append(" Berry\n");
            }
            if (lemon.isChecked){
                res.append(" Lemon\n");
            }
            if (pecan.isChecked){
                res.append(" Pecan\n");
            }
            res2.text = res;
            //Toast.makeText(applicationContext, res.toString(), Toast.LENGTH_LONG).show()

        }

    }
}

