package com.example.plantnexus_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class ScanResults : AppCompatActivity() {
    private lateinit var Name_search            : TextView
    private lateinit var varient_search         : TextView
    private lateinit var price_search           : TextView
    private lateinit var mode_search            : TextView
    private lateinit var productimg             : ImageView
    private lateinit var btnplaceOrder_search   : CardView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_results);

        init()

    }

    private fun init(){
        Name_search = findViewById(R.id.Name_search)
        varient_search = findViewById(R.id.varient_search)
        price_search = findViewById(R.id.price_search)
        mode_search = findViewById(R.id.mode_search)
        productimg = findViewById(R.id.productimg)
        btnplaceOrder_search = findViewById(R.id.btnplaceOrder_search)
    }

}