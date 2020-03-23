package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegroceryapp.R
import com.example.thegroceryapp.adapters.AdapterSelectAddress
import com.example.thegroceryapp.models.Address
import kotlinx.android.synthetic.main.activity_select_address.*

class SelectAddressActivity : AppCompatActivity() {

    lateinit var adapterSelectAddress: AdapterSelectAddress
    var list = ArrayList<Address>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_address)
        init()
    }

    private fun init() {
        recycler_view_select_address.layoutManager = LinearLayoutManager(this)
        adapterSelectAddress = AdapterSelectAddress(this, list)
        recycler_view_select_address.adapter = adapterSelectAddress

//        var address = intent.getSerializableExtra(Address.KEY_ADDRESS) as Address

      //  list.add(address)
        button_add_address_SAA.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))

        }
    }
}
