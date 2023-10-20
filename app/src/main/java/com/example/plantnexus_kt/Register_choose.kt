package com.example.plantnexus_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Register_choose : AppCompatActivity() {
    private lateinit var customerbtn:ImageButton
    private lateinit var sellerbtn:ImageButton
    private lateinit var login:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_choose)

        init()
        println("");

    }

    private fun init(){
        customerbtn = findViewById(R.id.customer)
        sellerbtn = findViewById(R.id.seller)
        login = findViewById(R.id.login)
    }
}