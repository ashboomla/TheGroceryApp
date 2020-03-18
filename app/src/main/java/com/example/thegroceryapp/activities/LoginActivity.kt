package com.example.thegroceryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thegroceryapp.R
import com.example.thegroceryapp.helpers.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        var sessionManager = SessionManager(this)

        if (sessionManager.checkLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        button_login_Login.setOnClickListener {
            var email = edit_text_email_Login.text.toString()
            var password = edit_text_password_Login.text.toString()

            if (sessionManager.login(email, password)) {
                Toast.makeText(this, "Login Successful\nWelcome!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "invalid Login  \nPlease try agian", Toast.LENGTH_SHORT).show()

            }
        }

        button_skip_Login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
