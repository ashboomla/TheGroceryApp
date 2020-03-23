package com.example.thegroceryapp.models

import java.io.Serializable

data class Address(
    var type: String,
    var name: String,
    var house_apt_no: String?,
    var street: String?,
    var city: String?,
    var state: String?,
    var zip: String
):Serializable{
    companion object {
        const val KEY_ADDRESS = "address"
    }
    fun getAddress() : String{
        var address: String = "$house_apt_no $street \n$city , $state"
        return address
    }
}