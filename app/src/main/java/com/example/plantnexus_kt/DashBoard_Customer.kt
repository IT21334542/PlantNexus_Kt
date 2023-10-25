package com.example.plantnexus_kt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantnexus_kt.Adapters.ProductAdaptor
import com.example.plantnexus_kt.Models.Plants
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
    private lateinit var blogthree      : CardView
    private lateinit var dragger        : RelativeLayout
    private lateinit var holderr        : RelativeLayout
    private lateinit var rec_products   : RecyclerView

    private lateinit var rec_products_grid  : RecyclerView
    private lateinit var ok : OkHttpClient
    private  var click = false;
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

            override fun onResponse(call: Call, response: Response)
            {
                val responseData = response.body?.string();
                Log.d("DATAFEATCHED",responseData.toString());

            }

        })
        val urI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXXSw2WdbA_N-adSzC8inRb41z191p-DVktreRD5W4xq4UoZIMSKE9KmwD7uCwfdsj4t4&usqp=CAU"

        val p1 = Plants("Cactus",urI,3300.00);
        val p2 = Plants("Cactus2",urI,3300.00);
        val p3 = Plants("Cactus2",urI,3300.00);
        val p4 = Plants("Cactus2",urI,3300.00);
        val p5 = Plants("Cactus2",urI,3300.00);

        val PlantsList = ArrayList<Plants>()
        PlantsList.add(p1);
        PlantsList.add(p2);
        PlantsList.add(p3);
        PlantsList.add(p4);
        PlantsList.add(p5);

        val ADAPTER = ProductAdaptor(PlantsList,this@DashBoard_Customer);
        rec_products.layoutManager = LinearLayoutManager(this@DashBoard_Customer,LinearLayoutManager.HORIZONTAL,false)
        rec_products.adapter = ADAPTER

        rec_products_grid.layoutManager = GridLayoutManager(this@DashBoard_Customer,2);
        rec_products_grid.adapter =ADAPTER

        dragger.setOnClickListener(View.OnClickListener {
            click=!click
 //Plant page is in MInimized

            if(click.not()) {

                val prams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    450
                )
                prams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)


                rec_products.layoutManager = LinearLayoutManager(this@DashBoard_Customer,LinearLayoutManager.HORIZONTAL,false)
                holderr.layoutParams = prams
                rec_products_grid.visibility= View.GONE

            }
            else
            {
//Plant page is in Expanded
                val tileprams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )

                tileprams.addRule(RelativeLayout.ALIGN_PARENT_TOP,50)
                val prams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )
                prams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)


                rec_products.layoutManager = LinearLayoutManager(this@DashBoard_Customer,LinearLayoutManager.HORIZONTAL,false)

                holderr.layoutParams = prams
                rec_products_grid.visibility= View.VISIBLE

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
        blogone = findViewById(R.id.blogone)
        blogtwo = findViewById(R.id.blogtwo)
        blogthree = findViewById(R.id.blogthree)
        dragger = findViewById(R.id.dragger)
        holderr = findViewById(R.id.rec_holder_dash)
        rec_products = findViewById(R.id.products_dash)
        ok = OkHttpClient()
        fetchurl = Request.Builder().url(url).build()
        rec_products_grid = findViewById(R.id.products_dash_grid)
    }
}