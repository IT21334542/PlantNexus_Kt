package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantnexus_kt.Adapters.ItemAdapter
import com.example.plantnexus_kt.Adapters.ProdAdapter
import com.example.plantnexus_kt.Adapters.ProductAdaptor
import com.example.plantnexus_kt.Models.Plants

private lateinit var backNavi : ImageView
private lateinit var homeNavi : ImageView
private lateinit var btnExpand : ImageView
private lateinit var btnminimize : ImageView
private lateinit var recProducts : RecyclerView
private lateinit var recCard : RecyclerView
private lateinit var total : TextView
private lateinit var itemsR  : TextView






class OnShop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_shop)

        initAll()
        onCLICKS()

        val ListProducts = ArrayList<Plants>()
        val ListItems = ArrayList<Plants>()


      //Plant
        val urI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXXSw2WdbA_N-adSzC8inRb41z191p-DVktreRD5W4xq4UoZIMSKE9KmwD7uCwfdsj4t4&usqp=CAU"
        val P1 = Plants("Cactus",urI,3300.00);


        ListProducts.add(P1);
        ListItems.add(P1)
     //Adapators and Layouts for Recyclers

        //ProductRecycler
        val PlantsAdap = ProdAdapter(ListProducts,this@OnShop)

        recProducts.layoutManager = LinearLayoutManager(this@OnShop)
        recProducts.adapter = PlantsAdap

        //ItemRecycler
        val ItemAdap = ItemAdapter(ListItems ,this@OnShop)

        recCard.layoutManager = LinearLayoutManager(this@OnShop)
        recCard.adapter = ItemAdap


    }

    private fun onCLICKS() {
        backNavi.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@OnShop,PlaceOrder::class.java))
        })
        homeNavi.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@OnShop,DashBoard_Customer::class.java))
        })
        btnminimize.setOnClickListener(View.OnClickListener {
            btnExpand.visibility = View.VISIBLE;
            recCard.visibility = View.VISIBLE;
            btnminimize.visibility = View.INVISIBLE
        })

        btnExpand.setOnClickListener(View.OnClickListener {
            btnExpand.visibility = View.GONE;
            recCard.visibility = View.GONE;
            btnminimize.visibility = View.VISIBLE
        })
    }

    private fun initAll() {
         backNavi  = findViewById(R.id.back_or)
         homeNavi  = findViewById(R.id.home_or)
         btnExpand  = findViewById(R.id.btnExpand)
         btnminimize  = findViewById(R.id.btnMinim)
         recProducts  = findViewById(R.id.ProductRec)
         recCard  = findViewById(R.id.rec_card)
         total = findViewById(R.id.cardTotal)
         itemsR  = findViewById(R.id.carditems)
    }
}