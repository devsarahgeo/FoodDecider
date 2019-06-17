package com.sarah.dinnerdecider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sarah.dinnerdecider.Model.Food
import kotlinx.android.synthetic.main.activity_add_food.*

class AddFood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)
        val db:DBHelper = DBHelper(this)

        addFoodBtn.setOnClickListener{
            val food=Food(addCuisineTxt.text.toString(),addFoodTxt.text.toString(),Integer.parseInt(addPriceTxt.text.toString()),addHotelName.text.toString(),Integer.parseInt(addPhoneNumber.text.toString()))
            db.addFood(food)
            addFoodTxt.text.clear()
            addCuisineTxt.text.clear()
            addPriceTxt.text.clear()
            addTags.text.clear()
            addHotelName.text.clear()
            addPhoneNumber.text.clear()
            finish()
        }

    }
}
