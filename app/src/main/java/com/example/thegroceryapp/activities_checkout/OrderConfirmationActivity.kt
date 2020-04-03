package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.MainActivity
import com.example.thegroceryapp.adapters.AdapterCart
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import kotlinx.android.synthetic.main.activity_order_confirmation.*

class OrderConfirmationActivity : AppCompatActivity(){

    lateinit var cart: ArrayList<Product>
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)
        dbHelper = DBHelper(this)

        init()
        cart = dbHelper.readCart() //array  list of product


        for (each in cart) {
            dbHelper.deleteCart(cart[0]) //remove from db

        }
    }

    private fun init() {
        button_continue_shopping_OCA.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}
