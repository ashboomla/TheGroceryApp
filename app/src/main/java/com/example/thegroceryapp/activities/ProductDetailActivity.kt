package com.example.thegroceryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import com.example.thegroceryapp.appData.Config
import com.example.thegroceryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var product : Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        init()
    }

    private fun init() {
        product =
            intent.getSerializableExtra(Product.KEY_PRODUCT) as Product
        text_view_name_PDA.text = product.productName
        text_view_desc_PDA.text = product.description

        Picasso
            .with(this)
            .load(Config.IMAGE_URL+product.image)//the path
            .placeholder(R.drawable.image_place_holder)
            .error(R.drawable.image_place_holder)
            .into(image_view_PDA)    }
}
