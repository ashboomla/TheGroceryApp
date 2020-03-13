package com.example.thegroceryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thegroceryapp.R
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        checkLogin()
        init()
    }

    private fun init() {
        button_login_Launch.setOnClickListener(this)
        button_register_Launch.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.button_register_Launch -> {
                startActivity(Intent(this,RegisterActivity::class.java))
            }
            R.id.button_login_Launch -> {
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }

    }
    private fun checkLogin() {
        var sharedPreference = getSharedPreferences("my_login", Context.MODE_PRIVATE)
        var status = sharedPreference.getBoolean("isLoggedIn", false)
        if(status){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
