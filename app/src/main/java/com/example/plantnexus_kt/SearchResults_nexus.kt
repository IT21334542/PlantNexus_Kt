package com.example.plantnexus_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SearchResults_nexus : AppCompatActivity() {
    private lateinit var home_serach          : ImageView
    private lateinit var navigateback_serach  : TextView
    private lateinit var aboutplant           : TextView
    private lateinit var txttiltle            : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results_nexus)
        init()
    }

    private fun init(){
        home_serach = findViewById(R.id.home_serach)
        navigateback_serach = findViewById(R.id.navigateback_serach)
        aboutplant = findViewById(R.id.aboutplant)
        txttiltle = findViewById(R.id.txttiltle)
    }
}