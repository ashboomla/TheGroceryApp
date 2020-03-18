package com.example.thegroceryapp.models

import java.io.Serializable

data class Category(
    val __v: Int,
    val _id: String,
    val catDescription: String,
    val catId: Int,
    val catImage: String,
    val catName: String,
    val position: Int,
    val slug: String,
    val status: Boolean
) :Serializable{
//generate --> kotlin dataclass from json
//how do you get the generate plugin
    companion object{
    const val KEY_CATEGORY = "category"
}
}
