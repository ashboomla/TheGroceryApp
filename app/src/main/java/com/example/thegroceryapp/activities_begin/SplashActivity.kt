package com.example.thegroceryapp.activities_begin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.thegroceryapp.R

class SplashActivity : AppCompatActivity() {
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 5000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            //this executes once the timer is over

            startActivity(Intent(this,LaunchActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)
    }
}
