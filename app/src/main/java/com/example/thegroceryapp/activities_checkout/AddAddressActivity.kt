package com.example.thegroceryapp.activities_checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import kotlinx.android.synthetic.main.activity_add_address.*

class AddAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
    init()
    }

    private fun init() {
        var name : String = edit_text_name_AAA.toString()
        var housenum : String = edit_text_house_AAA.toString()
        var street: String = edit_text_street_AAA.toString()
        var city: String =edit_text_city_AAA.toString()
        var state: String = edit_text_state_AAA.toString()
        var zip: String = edit_text_zip_AAA.toString()

        button_save_address_AAA.setOnClickListener {
            //this button should add an item to the array list on AdapterSelectAddress
        }

                /**/
    }
}
