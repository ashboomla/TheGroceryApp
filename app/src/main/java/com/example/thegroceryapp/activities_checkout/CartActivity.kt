package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.MainActivity
import com.example.thegroceryapp.adapters.AdapterCart
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.app_bar.*

class CartActivity : AppCompatActivity() {
    lateinit var adapterCart: AdapterCart
    lateinit var data: ArrayList<Product>
    // var mList: ArrayList<Product> = ArrayList() //Create an arrayList of the Category DC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        init()
        setupToolBar()

    }

    private fun setupToolBar() {
        var toolbar = myCustomToolbar //assigns the id for the toolbar with the variable toolbar
        toolbar.title = "Your Cart"
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart -> {
                Toast.makeText(this, "go to cart", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CartActivity::class.java))
            }

            R.id.menu_home -> {
                Toast.makeText(this, "go home", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            android.R.id.home -> {
                finish()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
        return true
    }


    private fun init() {
        var dbHelper = DBHelper(this)
        data = dbHelper.readCart() //array  list of product
        Log.i("data", data.size.toString())

        recycler_view_cart.layoutManager = LinearLayoutManager(this)
        adapterCart =
            AdapterCart(this)//creates an instance of the adapter cart ; so you can use whatever is inside it.
        adapterCart.setData(data) //^^ this is why I can get this function.
        recycler_view_cart.adapter = adapterCart

        getTotals()

    }

    private fun getTotals() {
        var totalPrice: Double = 0.0 //mrp
        var totalDiscount: Double = 0.0 //discount
        var totalPricetoPay: Double = 0.0 //mrp - discount
        for (each in data) {
            totalPricetoPay += (each.quantity * each.price)
            text_view_checkout_price_CA.text = totalPricetoPay.toString()

            totalDiscount += (each.quantity * (each.mrp - each.price))
            text_view_discount_price_CA.text = totalDiscount.toString()
            totalPrice += (each.quantity * each.mrp)
            text_view_total_price_CA.text = totalPrice.toString()


        }
    }


}
