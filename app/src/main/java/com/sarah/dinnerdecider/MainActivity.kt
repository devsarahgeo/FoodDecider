package com.sarah.dinnerdecider

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var db:DBHelper
    lateinit var number: Number
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DBHelper(this)
        val randomn = Random()
        val randomnFood = db.allFood.get(randomn.nextInt(db.allFood.size))
        tvCusiner.text = randomnFood.cuisine
        tvDishr.text = randomnFood.dish
        tvPricer.text = randomnFood.price.toString()
        tvRestr.text = randomnFood.hotel.toString()
         number = randomnFood.phone!!.toInt()
        decideBtn.setOnClickListener{
            val randomn = Random()
            val randomnFood = db.allFood.get(randomn.nextInt(db.allFood.size))
            tvCusiner.text = randomnFood.cuisine
            tvDishr.text = randomnFood.dish
            tvPricer.text = randomnFood.price.toString()
            tvRestr.text = randomnFood.hotel.toString()
            number = randomnFood.phone!!.toInt()
        }

        btnadd.setOnClickListener{
            val intent = Intent(this@MainActivity,AddFood::class.java)
            startActivity(intent)
        }
        btncall.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.menuaddFood){
            val intent = Intent(this@MainActivity,FoodList::class.java)
            startActivity(intent)
        }
        return true
    }
}
