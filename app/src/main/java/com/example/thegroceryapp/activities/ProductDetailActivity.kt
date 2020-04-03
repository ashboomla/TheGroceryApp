package com.example.thegroceryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities_checkout.CartActivity
import com.example.thegroceryapp.appData.Config
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.app_bar.*

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var product: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        init()
        setupToolBar()
    }

    private fun getQuantity(product:Product):Int{
        var dbHelper = DBHelper(this)
        var quantity:Int = dbHelper.getCartCount(product)
        return quantity
    }


    private fun setupToolBar() {
        var toolbar = myCustomToolbar //assigns the id for the toolbar with the variable toolbar
        toolbar.title = "${product.productName}"
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
                //finish()//try in front it will still work
                Toast.makeText(this, "go to cart", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home ->
            {
                finish()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }


    private fun init() {
        product = intent.getSerializableExtra(Product.KEY_PRODUCT) as Product
        text_view_name_PDA.text = product.productName
        text_view_desc_PDA.text = product.description
        text_view_price_PDA.text = "$${product.price.toString()}"
        text_view_mrp_PDA.text = "$${product.mrp.toString()}"

        Picasso //get image
            .get()
            .load(Config.IMAGE_URL + product.image)//the path
            .placeholder(R.drawable.image_place_holder)
            .error(R.drawable.image_place_holder)
            .into(image_view_PDA)

        button_add_to_cart.setOnClickListener(this)
        button_add_Multi_PDA.setOnClickListener(this)
        button_sub_Multi_PDA.setOnClickListener(this)

        var dbHelper = DBHelper(this) //passing an object of dbHelper to
        if (dbHelper.isItemInCart(product) == true) {//which button view should be visible
            button_add_to_cart.visibility = View.GONE // will hide the button.
            button_add_Multi_PDA.visibility = View.VISIBLE  //will show the button
            button_Req_Multi_PDA.visibility = View.VISIBLE
            button_Req_Multi_PDA.text = getQuantity(product).toString() // --> will get the current quantity from the db
            button_sub_Multi_PDA.visibility = View.VISIBLE
        }
       /* else {
            button_add_to_cart.visibility = View.VISIBLE // will hide the button.
            button_add_Multi_PDA.visibility = View.GONE  //will show the button
            button_Req_Multi_PDA.visibility = View.GONE
            button_Req_Multi_PDA.text = getQuantity(product).toString() // --> will get the current quantity from the db
            button_sub_Multi_PDA.visibility = View.GONE
        }*/

        }




    override fun onClick(view: View?) {    //BUTTON_ON_CLICKS
        var value: Int = button_Req_Multi_PDA.text.toString().toInt()


        when (view?.id) {
            R.id.button_add_to_cart -> {
                var dbHelper = DBHelper(this) //passing an object of dbHelper to
                dbHelper.addCart(product)
                    Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()

                    button_add_to_cart.visibility = View.GONE // will hide the button.
                    button_add_Multi_PDA.visibility = View.VISIBLE  //will show the button
                    button_Req_Multi_PDA.visibility = View.VISIBLE
                    button_Req_Multi_PDA.text = getQuantity(product).toString() // --> will get the current quantity from the db
                    button_sub_Multi_PDA.visibility = View.VISIBLE

            }
            R.id.button_add_Multi_PDA -> {
                var dbHelper = DBHelper(this) //passing an object of dbHelper to
                if (dbHelper.isItemInCart(product) == true) {
                    dbHelper.updateCartCount(product, true)
                    Toast.makeText(this, "Product: " + product.productName + product.quantity, Toast.LENGTH_SHORT).show()
                    value++
                    button_Req_Multi_PDA.text = value.toString()
                }
            }

            R.id.button_sub_Multi_PDA -> {
                var value: Int = button_Req_Multi_PDA.text.toString().toInt()
                var dbHelper = DBHelper(this) //passing an object of dbHelper to

                if(value>0){
                    dbHelper.updateCartCount(product, false)
                    Toast.makeText(this, "Product: " + product.productName + product.quantity, Toast.LENGTH_SHORT).show()
                   // var value: Int = button_Req_Multi_PDA.text.toString().toInt()
                    value--
                    button_Req_Multi_PDA.text = value.toString()
                }
                if(value<1 ||value ==0)/*else*/{
                    dbHelper.deleteCart(product) //remove from db
                    button_add_to_cart.visibility = View.VISIBLE // will hide the button.
                    button_add_Multi_PDA.visibility = View.GONE  //will show the button
                    button_Req_Multi_PDA.visibility = View.GONE
                    button_sub_Multi_PDA.visibility = View.GONE
                }

            }

          /*  R.id.button_go_to_cart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }*/
        }
    }

    override fun onRestart() {

        button_Req_Multi_PDA.text = getQuantity(product).toString() //important


        if(button_Req_Multi_PDA.text=="0"){
            button_add_to_cart.visibility = View.VISIBLE // will hide the button.
            button_add_Multi_PDA.visibility = View.GONE  //will show the button
            button_Req_Multi_PDA.visibility = View.GONE
            button_sub_Multi_PDA.visibility = View.GONE

        }

        super.onRestart()
    }
}