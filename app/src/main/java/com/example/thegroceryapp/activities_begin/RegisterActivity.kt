package com.example.thegroceryapp.activities_begin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thegroceryapp.R
import com.example.thegroceryapp.helpers.SessionManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_register_Register.setOnClickListener {
            var name = edit_text_name_Register.text.toString()
            var email = edit_text_email_Register.text.toString()
            var password = edit_text_password_Register.text.toString()

            var auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "user is registered successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java)) //this returns a packageContext  //addonCompleteListener is the interface:

                        } else {
                            Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

        }
    }
}
