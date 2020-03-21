package com.example.thegroceryapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    var image: String,
    var _id: String,
    var productName: String,
    var description: String,
    var subId: String,
    var catId: String,
    var quantity: Int,
    var created: String,
    var mrp: Double,
    var position: Int,
    var price: Double,
    var status: Boolean,
    var unit: String
) : Serializable {
    companion object {
        const val KEY_PRODUCT = "product"
    }
}

//////////////////////////////////////////////////////////////////////////////////////
