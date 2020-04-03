package com.example.thegroceryapp.activities_checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thegroceryapp.R
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        init()
    }

    private fun init() {
        button_continue_PA.setOnClickListener { startActivity(Intent(this, OrderConfirmationActivity::class.java)) }
    }
}
