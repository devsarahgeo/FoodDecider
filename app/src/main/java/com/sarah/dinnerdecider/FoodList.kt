package com.sarah.dinnerdecider

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.sarah.dinnerdecider.Model.Food
import kotlinx.android.synthetic.main.activity_add_food.*
import kotlinx.android.synthetic.main.activity_food_list.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

class FoodList : AppCompatActivity() {
    lateinit var db:DBHelper
    var listFoods:List<Food> = ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)
        db = DBHelper(this)
        listFoods = db.allFood
        val adapter = FoodListAdapter(this,listFoods)
        food_list.adapter =adapter

    }
}

class FoodListAdapter:BaseAdapter{
    var context: Context?=null
    var listFood :List<Food> = ArrayList<Food>()
    constructor(context:Context,listFood:List<Food>):super(){
        this.context=context
        this.listFood = listFood
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row:View = inflater.inflate(R.layout.list_item,null)
        row.licuisine.text = listFood[position].cuisine.toString()
        row.lidish.text = listFood[position].dish.toString()
        return row

    }

    override fun getItem(position: Int): Any {
        return listFood[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listFood.size
    }

}
