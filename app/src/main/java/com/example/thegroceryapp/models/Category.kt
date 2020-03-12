package com.example.thegroceryapp.models

import java.io.Serializable

data class Category(
    var catImage: String,
    var catId: Int,
    var catName: String
) :Serializable{
//generate --> kotlin dataclass from json
//how do you get the generate plugin
    companion object{
    const val KEY_CATEGORY = "category"
}
}