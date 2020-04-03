package com.example.thegroceryapp.models

import java.io.Serializable

data class Address(
    var key: String? = null,
    var type: String?=null,
    var name: String?=null,
    var house_apt_no: String?=null,
    var street: String?=null,
    var city: String?=null,
    var state: String?=null,
    var zip: String?=null
):Serializable{
    companion object {
        const val KEY_ADDRESS = "address"
    }
    fun getAddress() : String{
        var addressC: String = "$house_apt_no $street \n$city , $state"
        return addressC
    }
}