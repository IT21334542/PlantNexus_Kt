package com.example.plantnexus_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class Register : AppCompatActivity() {
    private lateinit var fullname:EditText
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var contactnumber:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registercustomer)

        init()
    }

    private fun init(){
        fullname = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password_register)
        contactnumber = findViewById(R.id.phonenumber)
    }
}