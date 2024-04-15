package com.example.lab7_q1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "Lab7"
val TABLE_NAME = "Students"
val COL_ID = "id"
val COL_REGNO = "regno"
val COL_NAME = "name"
val COL_MARKS = "marks"



class DataBaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ( $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_REGNO VARCHAR(256), $COL_NAME VARCHAR(256), $COL_MARKS VARCHAR(256))"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(student: Student) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_REGNO, student.regno)
        cv.put(COL_NAME, student.name)
        cv.put(COL_MARKS, student.marks)

        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("Range")
    fun readData(): MutableList<Student>{
        var list: MutableList<Student> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                var student = Student()
                student.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                student.regno = result.getString(result.getColumnIndex(COL_REGNO))
                student.name = result.getString(result.getColumnIndex(COL_NAME))
                student.marks = result.getString(result.getColumnIndex(COL_MARKS))
                list.add(student)
            }while(result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

    fun deleteData(){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, null, null )

        db.close()
    }
    @SuppressLint("Range")
    fun updateData() {

        val db = this.writableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                var cv = ContentValues()
                cv.put(COL_MARKS, (result.getInt(result.getColumnIndex(COL_MARKS)) + 5))
                db.update(TABLE_NAME,cv, COL_ID + "=? ",
                    arrayOf(result.getString(result.getColumnIndex(COL_ID))))
            }while(result.moveToNext())
        }

        result.close()
        db.close()

    }
}

