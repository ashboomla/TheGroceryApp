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
    /*,

    val created: String,
    val mrp: Int,
    val position: Int,
    val price: Double,

    val quantity: Int,
    val status: Boolean,

    val unit: String*/
) : Serializable {
    //generate --> kotlin dataclass from json
//how do you get the generate plugin
    companion object {
        const val KEY_PRODUCT = "product"
    }
}

//////////////////////////////////////////////////////////////////////////////////////
