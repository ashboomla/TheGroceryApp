package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import kotlinx.android.synthetic.main.activity_select_address.*

class SelectAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_address)
    init()
    }

    private fun init() {
        button_add_address_SAA.setOnClickListener {
            startActivity(Intent(this,AddAddressActivity::class.java))

        }
    }
}
