package com.example.thegroceryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.thegroceryapp.R
import com.example.thegroceryapp.appData.Config
import com.example.thegroceryapp.helpers.DBHelper
import com.example.thegroceryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var product: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        init()
    }

    private fun init() {
        product = intent.getSerializableExtra(Product.KEY_PRODUCT) as Product
        text_view_name_PDA.text = product.productName
        text_view_desc_PDA.text = product.description
        text_view_price_PDA.text = product.price.toString()
        text_view_mrp_PDA.text = product.mrp.toString()

        Picasso //get image
            .get()
            .load(Config.IMAGE_URL + product.image)//the path
            .placeholder(R.drawable.image_place_holder)
            .error(R.drawable.image_place_holder)
            .into(image_view_PDA)

        button_add_to_cart.setOnClickListener(this)
        button_go_to_cart.setOnClickListener(this)
    }


    override fun onClick(view: View?) {    //BUTTON_ON_CLICKS
       // var count = 1

        when (view?.id) {
            R.id.button_add_to_cart -> {
                var dbHelper = DBHelper(this)
                if (dbHelper.isItemInCart(product) == true) {
                    //count++
                    //product.qtyReq = count
                    dbHelper.updateCartCount(product,true)
                    Toast.makeText(
                        this,
                        "Product: " + product.productName + product.quantity,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    dbHelper.addCart(product)
                    Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button_go_to_cart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }
    }


}

