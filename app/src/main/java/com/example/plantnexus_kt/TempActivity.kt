package com.example.plantnexus_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TempActivity : AppCompatActivity() {

    private lateinit var backN :ImageView
    private lateinit var HomeN :ImageView
    private lateinit var weatherIcon :ImageView
    private lateinit var location :TextView
    private lateinit var phasetxt :TextView
    private lateinit var calcuis :TextView
    private lateinit var gide :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)
        initall()
        val  url = ""
        val request = Request.Builder().url(url).build()
        val ok = OkHttpClient()
        ok.newCall(request).enqueue(object  : Callback
        {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("TempCall - error",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
            }

        })

    }

    private fun initall() {
        backN = findViewById(R.id.back_or)
        HomeN = findViewById(R.id.home_or)
        weatherIcon = findViewById(R.id.tempPhases)
        location = findViewById(R.id.mini)
        phasetxt = findViewById(R.id.location)
        calcuis = findViewById(R.id.CelciusValue)
        gide= findViewById(R.id.guide)
    }
}