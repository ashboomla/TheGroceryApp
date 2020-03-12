package com.example.thegroceryapp.models

data class CategoryResponse(
    var error: Boolean,
    var count: Int,
    var data: ArrayList<Category> //array list that contains the model class
) {
}

