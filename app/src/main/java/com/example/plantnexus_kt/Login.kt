package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var username :EditText
    private lateinit var password :EditText
    private lateinit var login : Button
    private lateinit var signup : Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()

        login.setOnClickListener {
            val uname = username.text.toString()
            val pass = password.text.toString()
            if (uname == "test" && pass == "test") {
                Log.v("pass","Login Success")
                startActivity(Intent(this@Login, DashBoard_Customer::class.java))
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show()
            }
        }

        signup.setOnClickListener{
            startActivity(Intent(this@Login,Register_choose::class.java))
        }


    }

    private fun init(){
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        signup = findViewById(R.id.signup)
    }
}