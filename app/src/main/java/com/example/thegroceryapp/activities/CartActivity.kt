package com.example.thegroceryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterCart
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_cart_adapter.*

class CartActivity : AppCompatActivity() {
    lateinit var adapterCart: AdapterCart
    // var mList: ArrayList<Product> = ArrayList() //Create an arrayList of the Category DC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        init()

    }

    private fun init() {
        var dbHelper = DBHelper(this)
        var data = dbHelper.readCart()
        Log.i("data", data.size.toString())

        recycler_view_cart.layoutManager = LinearLayoutManager(this)
        adapterCart =
            AdapterCart(this)//creates an instance of the adapter cart ; so you can use whatever is inside it.
        adapterCart.setData(data) //^^ this is why I can get this function.
        recycler_view_cart.adapter = adapterCart


    }


}
