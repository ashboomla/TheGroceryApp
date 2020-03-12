package com.example.thegroceryapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    var image: String,
    var id: Int,
    var productName: String,
    var description: String,
    var subId: String,
    var catId: String

) : Serializable {
    //generate --> kotlin dataclass from json
//how do you get the generate plugin
    companion object {
        const val KEY_PRODUCT = "product"
    }
}