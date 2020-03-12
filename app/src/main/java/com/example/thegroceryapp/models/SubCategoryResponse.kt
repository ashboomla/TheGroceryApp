package com.example.thegroceryapp.models

import java.io.Serializable

data class SubCategoryResponse(
    var error: Boolean,
    var count: Int,
    var data: ArrayList<SubCategory>
): Serializable {
    //generate --> kotlin dataclass from json
//how do you get the generate plugin
    companion object{
        const val KEY_SUB_CATEGORY = "sub_category"
    }
}