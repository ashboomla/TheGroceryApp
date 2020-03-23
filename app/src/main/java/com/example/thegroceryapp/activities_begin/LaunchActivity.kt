package com.example.thegroceryapp.activities_begin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.MainActivity
import com.example.thegroceryapp.helpers.SessionManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_launch.*
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser

class LaunchActivity : AppCompatActivity(), View.OnClickListener {
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

// Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        init()



    }

    private fun init() {
        button_login_Launch.setOnClickListener(this)
        button_register_Launch.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_register_Launch -> { startActivity(Intent(this, RegisterActivity::class.java)) }
            R.id.button_login_Launch -> { startActivity(Intent(this, LoginActivity::class.java)) }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        (currentUser)
    }

}
