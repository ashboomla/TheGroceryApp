package com.example.thegroceryapp.models

import java.io.Serializable

data class Address(
    var name: String,
    var type: String,
    var house_apt_no: String?,
    var street: String?,
    var city: String?,
    var state: String?,
    var address: String = "$house_apt_no $street \n$city , $state",
    var zip: String
):Serializable{
    companion object {
        const val KEY_PRODUCT = "address"
    }
}