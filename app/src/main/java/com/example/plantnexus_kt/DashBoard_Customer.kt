package com.example.plantnexus_kt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class DashBoard_Customer : AppCompatActivity() {

    private lateinit var card_scanplnat : CardView
    private lateinit var card_myplants  : CardView
    private lateinit var card_chatbot   : CardView
    private lateinit var card_orders    : CardView
    private lateinit var blogone        : CardView
    private lateinit var blogtwo        : CardView
    private lateinit var Plantcardone   : CardView
    private lateinit var Plantcardtwo   : CardView
    private lateinit var Plantcardthree : CardView
    private lateinit var ok : OkHttpClient
    private lateinit var fetchurl : Request
    val url:String = "https://us-east-1.aws.data.mongodb-api.com/app/procurementx1-msxsm/endpoint/Plants"
    val DATAFETCHED = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_customer);

       init()

        ok.newCall(fetchurl).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
               e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string();
                Log.d("DATAFEATCHED",responseData.toString());

            }

        })


//        Handler(Looper.getMainLooper()).postDelayed({
//            val to : Intent = Intent(this@DashBoard_Customer, DashBoard_Customer::class.java);
//            startActivity(to);
//            finish()
//        },1000);

    }
    private fun init(){
        card_scanplnat = findViewById(R.id.card_scanplnat)
        card_myplants = findViewById(R.id.card_myplants)
        card_chatbot = findViewById(R.id.card_chatbot)
        card_orders = findViewById(R.id.card_orders)
//        blogone = findViewById(R.id.blogone)
//        blogtwo = findViewById(R.id.blogtwo)
//        Plantcardone = findViewById(R.id.Plantcardone)
//        Plantcardtwo = findViewById(R.id.Plantcardtwo)
//        Plantcardthree = findViewById(R.id.Plantcardthree)
        ok = OkHttpClient();
        fetchurl = Request.Builder().url(url).build()
    }
}