package com.sarah.dinnerdecider
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sarah.dinnerdecider.Model.Food

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VER) {
    companion object {
        val DATABASE_NAME = "FoodDb.db"
        val DATABASE_VER = 1
        val TABLE_NAME = "Food"
        val COL_ID = "Id"
        val COL_CUISINE = "Cuisine"
        val COL_DISH = "Dish"
        val COL_PRICE = "Price"
        val COL_HOTEL = "Hotel"
        val COL_PHONE = "Phone"
        val COL_DESCTAGS = "DescTags"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER AUTO_INCREMENT PRIMARY KEY,$COL_CUISINE TEXT,$COL_DISH TEXT,$COL_PRICE INTEGER,$COL_HOTEL TEXT,$COL_PHONE NUMBER)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }
    val allFood:List<Food>
    get(){
        val listItem = ArrayList<Food>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)
        if(cursor.moveToFirst()){
            do{
                val food = Food(cursor.getString(cursor.getColumnIndex(COL_CUISINE)),cursor.getString(cursor.getColumnIndex(COL_DISH)),cursor.getInt(cursor.getColumnIndex(COL_PRICE)),cursor.getString(cursor.getColumnIndex(COL_HOTEL)),cursor.getInt(cursor.getColumnIndex(COL_PHONE)))
                listItem.add(food)
            }while (cursor.moveToNext())
        }
        return listItem
    }

    fun addFood(food:Food){
        val db = this.writableDatabase
        val values = ContentValues()
//        values.put(COL_ID,food.foodId)
        values.put(COL_CUISINE,food.cuisine)
        values.put(COL_DISH,food.dish)
        values.put(COL_PRICE,food.price)
        values.put(COL_HOTEL,food.hotel)
        values.put(COL_PHONE,food.phone)
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

//    fun deleteFood(food: Food){
//        val db = this.writableDatabase
//        db.delete(TABLE_NAME,"COL_ID=?", arrayOf(food.foodId.toString()))
//        db.close()
//    }


}