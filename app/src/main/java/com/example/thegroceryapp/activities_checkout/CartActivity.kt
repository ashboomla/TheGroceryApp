package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.MainActivity
import com.example.thegroceryapp.adapters.AdapterCart
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.row_cart_adapter.*
import kotlinx.android.synthetic.main.row_cart_adapter.view.*
import kotlinx.android.synthetic.main.row_cart_adapter.view.text_view_qtyReq_RCA

class CartActivity : AppCompatActivity(), AdapterCart.AdapterInteraction {
    lateinit var adapterCart: AdapterCart
    lateinit var cart: ArrayList<Product>
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        dbHelper = DBHelper(this)

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
        //var dbHelper = DBHelper(this)
        cart = dbHelper.readCart() //array  list of product
        Log.i("data", cart.size.toString())


        recycler_view_cart.layoutManager = LinearLayoutManager(this)
        adapterCart = AdapterCart(this, cart)//creates an instance of the adapter cart ; so you can use whatever is inside it.
        adapterCart.setAdapterInteraction(this)
        adapterCart.setData(cart) //^^ this is why I can get this function.
        recycler_view_cart.adapter = adapterCart

        getTotals()
        cartempty()


        button_checkout_CA.setOnClickListener {
            startActivity(Intent(this, SelectAddressActivity::class.java))
        }
    }

    private fun cartempty() {
        if (cart.isEmpty()) {
            no_items_CA.visibility = VISIBLE
            totals_block_layout_CA.visibility = INVISIBLE

        } else {
            no_items_CA.visibility = INVISIBLE
            totals_block_layout_CA.visibility = VISIBLE
        }
    }


    private fun getTotals() {

        var totalPrice: Double = 0.0 //mrp
        var totalDiscount: Double = 0.0 //discount
        var delivery: Double = 0.00 // delivery
        var checkoutPrice: Double = 0.0 //mrp - discount
        for (each in cart) {

            totalPrice += (each.quantity * each.mrp)
            text_view_total_price_CA.text = "$${String.format("%.2f",totalPrice).toString()}"

            totalDiscount += (each.quantity * (each.mrp - each.price))
            text_view_discount_price_CA.text = "$${String.format("%.2f",totalDiscount).toString()}"

            text_view_delivery_charges_CA.text = "$${String.format("%.2f",0.00).toString()}"

            checkoutPrice += (each.quantity * each.price)
            text_view_checkout_price_CA.text = "$${String.format("%.2f",(checkoutPrice+delivery)).toString()}"

        }
    }

    override fun onItemCLicked(position: Int, view: View) {
        var product = cart.get(position)
        var quan = product.quantity
        when (view.id) {

            R.id.button_add -> {
                quan++
                product.quantity = product.quantity + 1
                text_view_qtyReq_RCA.text = quan.toString()
                dbHelper.updateCartCount(cart[position], true)
                cart.set(position, product)
                adapterCart.notifyItemChanged(position)  //updates the cart
                var total: Double =
                    (cart[position].price * text_view_qtyReq_RCA.text.toString().toInt())
                text_view_total_RCA.text = "$${String.format("%.2f", total).toString()}"
            }
            R.id.button_sub -> {
                if (quan > 0) {
                    quan--
                    product.quantity = product.quantity - 1
                    text_view_qtyReq_RCA.text = quan.toString()
                    dbHelper.updateCartCount(cart[position], false) //update to db
                    cart.set(position, product)  // ??? sets the position of the product?
                    adapterCart.notifyItemChanged(position)
                    var total: Double =
                        (cart[position].price * text_view_qtyReq_RCA.text.toString().toDouble())
                    text_view_total_RCA.text = "$${String.format("%.2f", total).toString()}"

                }
                if (quan == 0) {
                    dbHelper.deleteCart(cart[position]) //remove from db
                    adapterCart.removeItem(position) // remove from recyclerView
                    adapterCart.notifyItemChanged(position)
                }

            }


            R.id.button_Remove_RCA -> {
                Toast.makeText(this, " " + position, Toast.LENGTH_SHORT).show()
                dbHelper.deleteCart(cart[position]) //remove from db
                adapterCart.removeItem(position) // remove from recyclerView

                var total: Double =
                    (cart[position].price * dbHelper.getCartCount(cart[position]))//get total on cart view
                text_view_total_RCA.text = "$${String.format("%.2f", total).toString()}"
            }
        }
        getTotals()
        cartempty()
    }
}
