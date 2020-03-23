package com.example.thegroceryapp.activities_checkout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterSelectAddress
import com.example.thegroceryapp.models.Address
import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddressActivity : AppCompatActivity() {

    lateinit var adapterSelectAddress: AdapterSelectAddress
    var asaList = ArrayList<Address>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        init()
    }

    private fun init() {
        var name: String = edit_text_name_AAA.text.toString()
        var type: String = edit_text_type_AAA.text.toString()
        var housenum: String = edit_text_house_AAA.text.toString()
        var street: String = edit_text_street_AAA.text.toString()
        var city: String = edit_text_city_AAA.text.toString()
        var state: String = edit_text_state_AAA.text.toString()
        var zip: String = edit_text_zip_AAA.text.toString()

        adapterSelectAddress = AdapterSelectAddress(this, asaList)

        button_save_address_AAA.setOnClickListener {
            //this button should add an item to the array list on AdapterSelectAddress
            var address =
                Address(name, type, housenum, street, city, state, zip) // object of address

            startActivity(Intent(this, SelectAddressActivity::class.java))

        }
    }
}
