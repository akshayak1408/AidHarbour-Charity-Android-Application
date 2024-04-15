package com.example.lab7_q1

class Student {
        var id: Int = 0
        var regno: String = ""
        var name: String = ""
        var marks: String = ""

        constructor(regno: String,name:String,marks:String){
            this.regno = regno
            this.name = name
            this.marks = marks
        }

        constructor(){

        }
}