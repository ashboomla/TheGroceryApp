package com.example.thegroceryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thegroceryapp.R
import com.example.thegroceryapp.helpers.SessionManager
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_register_Register.setOnClickListener{
            var name = edit_text_name_Register.text.toString()
            var email = edit_text_email_Register.text.toString()
            var password = edit_text_password_Register.text.toString()

            var sessionManager = SessionManager(this)
            sessionManager.register(name,email,password)
            Toast.makeText(this,"You've been Registered \n Welcome $name!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginActivity::class.java))

       /* var sharedPreferences = getSharedPreferences("my_login", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("email",email)
        editor.putString("password", password)
        editor.putBoolean("isLoggedIn",true)
        editor.commit()
        Toast.makeText(this,"You've been Registered \n Welcome $name!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,LoginActivity::class.java))*/
    }
    }
}
