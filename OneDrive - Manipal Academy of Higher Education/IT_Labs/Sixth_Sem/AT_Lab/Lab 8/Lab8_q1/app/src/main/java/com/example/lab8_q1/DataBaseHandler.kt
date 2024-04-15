package com.example.lab8_q1


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "ProductInfo"
val TABLE_NAME = "Products"
val COL_ID = "id"
val COL_NAME = "name"
val COL_COST = "cost"

class DataBaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ( $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NAME VARCHAR(256), $COL_COST REAL )"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(product: Product) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, product.name)
        cv.put(COL_COST, product.cost)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("Range")
    fun readData(): MutableList<Product>{
        var list: MutableList<Product> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                var product = Product()
                product.id = result.getInt(result.getColumnIndex(COL_ID))
                product.name = result.getString(result.getColumnIndex(COL_NAME))
                product.cost = result.getDouble(result.getColumnIndex(COL_COST))
                list.add(product)
            }while(result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

    fun deleteData(){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, COL_ID+"=?", arrayOf(1.toString()))

        db.close()
    }
    fun getTotalNumberOfProducts(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count
    }

    @SuppressLint("Range")
    fun getMaxPricedProduct(): Product? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COL_COST DESC LIMIT 1", null)
        val product = if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
            val cost = cursor.getDouble(cursor.getColumnIndex(COL_COST))
            Product(name, cost)
        } else {
            null
        }
        cursor.close()

        return product
    }

    @SuppressLint("Range")
    fun getMinPricedProduct(): Product? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COL_COST ASC LIMIT 1", null)
        val product = if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
            val cost = cursor.getDouble(cursor.getColumnIndex(COL_COST))
            Product(name, cost)
        } else {
            null
        }
        cursor.close()
        return product
    }
}

