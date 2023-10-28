package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantnexus_kt.Adapters.ItemAdapter
import com.example.plantnexus_kt.Adapters.ProdAdapter
import com.example.plantnexus_kt.Adapters.ProductAdaptor
import com.example.plantnexus_kt.Models.Plants
import com.example.plantnexus_kt.Models.Shops

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





      //Plant
        val SHOPS = intent.getSerializableExtra("SHOP") as Shops


        val ListProducts = SHOPS.PlantsSells
        val ListItems = ArrayList<Plants>()
     //Adapators and Layouts for Recyclers

        //ProductRecycler
        val PlantsAdap = ProdAdapter(ListProducts,this@OnShop,ListItems)

        recProducts.layoutManager = LinearLayoutManager(this@OnShop)
        recProducts.adapter = PlantsAdap

        //ItemRecycler
        val ItemAdap = ItemAdapter(ListItems ,this@OnShop)

        recCard.layoutManager = LinearLayoutManager(this@OnShop)
        recCard.adapter = ItemAdap



    }

    private fun onCLICKS() {
        backNavi.setOnClickListener(View.OnClickListener {
            try{
                startActivity(Intent(this@OnShop,PlaceOrder::class.java))
            }catch (E:Exception)
            {
                Log.d("backHomeError",E.toString())
            }
        })
        homeNavi.setOnClickListener(View.OnClickListener {
            try{
                startActivity(Intent(this@OnShop,PlaceOrder::class.java))
            }catch (E:Exception)
            {
                Log.d("backHomeError",E.toString())
            }
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