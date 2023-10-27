package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.plantnexus_kt.Models.Plants

class SearchResults_nexus : AppCompatActivity() {
    private lateinit var home_serach          : ImageView
    private lateinit var navigateback_serach  : ImageView
    private lateinit var PlantImage  : ImageView
    private lateinit var aboutplant           : TextView
    private lateinit var txttiltle            : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results_nexus)
        init()
        val plant = intent.getSerializableExtra("Plant") as Plants

        home_serach.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@SearchResults_nexus,DashBoard_Customer::class.java))
        })

        navigateback_serach.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@SearchResults_nexus,DashBoard_Customer::class.java))
        })

        Glide.with(this@SearchResults_nexus).asBitmap().load(plant.plantImagePreview).into(PlantImage)
        txttiltle.text = plant.plantname

    }

    private fun init(){
        home_serach = findViewById(R.id.home_or)
        navigateback_serach = findViewById(R.id.navigateback_serach)
        PlantImage = findViewById(R.id.imgsP)
        aboutplant = findViewById(R.id.aboutplant)
        txttiltle = findViewById(R.id.txttiltle)
    }
}