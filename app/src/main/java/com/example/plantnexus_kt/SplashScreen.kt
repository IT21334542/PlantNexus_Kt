package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("hello");

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intenter : Intent = Intent(this@SplashScreen,Dashboard_Deliveryboy::class.java);
                startActivity(intenter)
            },
            3000
        )




    }
}