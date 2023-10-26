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
import com.example.plantnexus_kt.Models.Plants
import com.example.plantnexus_kt.Models.Shops

private lateinit var backNavi :ImageView
private lateinit var home :ImageView
private lateinit var btnSearch :ImageView
private lateinit var ettext :EditText
private lateinit var rec_shoppers :RecyclerView
private lateinit var ShopsList : ArrayList<Shops>


class PlaceOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)
        init()

        clicks()

        val urI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXXSw2WdbA_N-adSzC8inRb41z191p-DVktreRD5W4xq4UoZIMSKE9KmwD7uCwfdsj4t4&usqp=CAU"
        val U ="https://res.cloudinary.com/sagacity/image/upload/c_crop,h_3333,w_5000,x_0,y_0/c_limit,dpr_2.625,f_auto,fl_lossy,q_80,w_412/0122_SeattleMet_UVillage_RavennaGardens_CarltonCanary_0255_n9y6pd.jpg"
        val p1 = Plants("Cactus",urI,3300.00);


        ShopsList = ArrayList()
        val s1 = Shops("GoldePlant",U,"Malabe, Sri Lanka")
        ShopsList.add(s1)

        val ADAPTOR:ShopsAdaptor  = ShopsAdaptor(this@PlaceOrder, ShopsList);


        rec_shoppers.layoutManager = LinearLayoutManager(this@PlaceOrder)
        rec_shoppers.adapter = ADAPTOR
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