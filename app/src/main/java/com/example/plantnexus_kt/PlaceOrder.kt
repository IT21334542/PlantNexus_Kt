package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantnexus_kt.Adapters.ProductAdaptor
import com.example.plantnexus_kt.Adapters.ShopsAdaptor
import com.example.plantnexus_kt.Models.Shops

private lateinit var backNavi :ImageView
private lateinit var home :ImageView
private lateinit var btnSearch :ImageView
private lateinit var ettext :EditText
private lateinit var rec_shoppers :RecyclerView
private val ShopsList : ArrayList<Shops> = ArrayList<Shops>()


class PlaceOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)
        init()

        clicks()



        val ADAPTOR:ShopsAdaptor  = ShopsAdaptor(this@PlaceOrder, ShopsList);

        rec_shoppers.adapter = ADAPTOR
        rec_shoppers.layoutManager = LinearLayoutManager(this@PlaceOrder)

    }

    private fun clicks() {
        home.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@PlaceOrder,DashBoard_Customer::class.java))
        })

        backNavi.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@PlaceOrder,ScanResults::class.java))
        })

        btnSearch.setOnClickListener(View.OnClickListener {

        })
    }

    fun init()
    {
        rec_shoppers = findViewById(R.id.rec_shoppers)
        ettext = findViewById(R.id.et_plantname)
        home = findViewById(R.id.home_or)
        btnSearch = findViewById(R.id.btnsearch)
        backNavi = findViewById(R.id.back_or)

    }
}