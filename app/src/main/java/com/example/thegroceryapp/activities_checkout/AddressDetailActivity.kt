package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import com.example.thegroceryapp.appData.ConfigAddress
import com.example.thegroceryapp.models.Address
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_address_detail.*

class AddressDetailActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    var address: Address? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_detail)

        databaseReference =
            FirebaseDatabase.getInstance().getReference(ConfigAddress.FIREBASE_DATABASE_NAME)

        address = intent.getSerializableExtra(Address.KEY_ADDRESS) as Address

        init()
    }

    private fun init() {
        edit_text_type_AAD.setText(address?.type)
        edit_text_name_AAD.setText(address?.name)
        edit_text_house_AAD.setText(address?.house_apt_no)
        edit_text_street_AAD.setText(address?.street)
        edit_text_city_AAD.setText(address?.city)
        edit_text_state_AAD.setText(address?.state)
        edit_text_zip_AAD.setText(address?.zip)

        button_remove_address_AAD.setOnClickListener {
            databaseReference.child(address?.key!!).setValue(null)
            startActivity(Intent(this, SelectAddressActivity::class.java))
            finish()
        }

        button_change_address_AAD.setOnClickListener {
            var name: String = edit_text_name_AAD.text.toString()
            var type: String = edit_text_type_AAD.text.toString()
            var housenum: String = edit_text_house_AAD.text.toString()
            var street: String = edit_text_street_AAD.text.toString()
            var city: String = edit_text_city_AAD.text.toString()
            var state: String = edit_text_state_AAD.text.toString()
            var zip: String = edit_text_zip_AAD.text.toString()

            var addressUpdate = Address()
            addressUpdate.type = type
            addressUpdate.name = name
            addressUpdate.house_apt_no = housenum
            addressUpdate.street = street
            addressUpdate.city = city
            addressUpdate.state = state
            addressUpdate.zip = zip

            databaseReference.child(address?.key!!).setValue(addressUpdate)
            startActivity(Intent(this, SelectAddressActivity::class.java))

        }
    }
}