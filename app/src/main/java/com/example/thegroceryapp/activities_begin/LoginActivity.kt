package com.example.thegroceryapp.activities_begin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.thegroceryapp.R
import com.example.thegroceryapp.activities.MainActivity
import com.example.thegroceryapp.helpers.SessionManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
     //   checkLogin()
    }



    private fun init() {
        button_login_Login.setOnClickListener {
            var email = edit_text_email_Login.text.toString()
            var password = edit_text_password_Login.text.toString()

            var auth = FirebaseAuth.getInstance()



        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this,object:OnCompleteListener<AuthResult>
            {
                override fun onComplete(task: Task<AuthResult>)
                {
                    if(task.isSuccessful)
                    {
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))

                        Toast.makeText(applicationContext,"success",Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(applicationContext,"failed" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        button_skip_Login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
