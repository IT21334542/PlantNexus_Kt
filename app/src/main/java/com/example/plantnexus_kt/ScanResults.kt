package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.plantnexus_kt.Models.Plants

class ScanResults : AppCompatActivity() {
    private lateinit var Name_search            : TextView
    private lateinit var varient_search         : TextView
    private lateinit var price_search           : TextView
    private lateinit var mode_search            : TextView
    private lateinit var Describtion            : TextView
    private lateinit var productimg             : ImageView
    private lateinit var Back             : ImageView
    private lateinit var Home             : ImageView
    private lateinit var btnplaceOrder_search   : CardView
    private lateinit var plant : Plants


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_results);

        init()

        Back.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@ScanResults,DashBoard_Customer::class.java))
        })

        Home.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@ScanResults,DashBoard_Customer::class.java))
        })

        //insiate
        Glide.with(this@ScanResults).asBitmap().load(plant.plantImagePreview).into(productimg)
        Name_search.text = plant.plantname
        varient_search.text = plant.varient
        price_search.text = plant.plantPrice.toString()
        mode_search.text = plant.Mode
        Describtion.text = plant.Description


        btnplaceOrder_search.setOnClickListener(View.OnClickListener {
            val to = Intent(this@ScanResults,PlaceOrder::class.java)
            startActivity(to)
        })


    }

    private fun init(){
        plant=intent.getSerializableExtra("PRODUCTSELECTED") as Plants
        Name_search = findViewById(R.id.Name_search)
        varient_search = findViewById(R.id.varient_search)
        price_search = findViewById(R.id.price_search)
        mode_search = findViewById(R.id.mode_search)
        productimg = findViewById(R.id.productimg)
        btnplaceOrder_search = findViewById(R.id.btnplaceOrder_search)
        Describtion =findViewById(R.id.plant_desc)
        Back =findViewById(R.id.navigateback_serach)
        Home = findViewById(R.id.home_serach)


    }

}