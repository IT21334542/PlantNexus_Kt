package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class Register_choose : AppCompatActivity() {
    private lateinit var customerbtn:ImageButton
    private lateinit var sellerbtn:ImageButton
    private lateinit var login:Button
    private lateinit var signCust: ImageView
    private lateinit var signSell: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_choose)

        init()
        login.setOnClickListener {
            startActivity(Intent(this@Register_choose,Login::class.java))
        }

        signCust.setOnClickListener { startActivity(Intent(this@Register_choose,Register::class.java)) }
        signSell.setOnClickListener { startActivity(Intent(this@Register_choose,Registerseller::class.java)) }


    }

    private fun init(){
        customerbtn = findViewById(R.id.customer)
        sellerbtn = findViewById(R.id.seller)
        login = findViewById(R.id.login)
        signCust = findViewById(R.id.customer)
        signSell = findViewById(R.id.seller)
    }
}