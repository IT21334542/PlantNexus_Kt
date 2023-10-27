package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

private lateinit var backNavi :ImageView
private lateinit var home :ImageView
private lateinit var btnSearch :ImageView
private lateinit var ettext :EditText
private lateinit var rec_shoppers :RecyclerView
private lateinit var ShopsList : ArrayList<Shops>
private val SupplierEndPoint ="https://us-east-1.aws.data.mongodb-api.com/app/procurementx1-msxsm/endpoint/Suppliers"
private val ok =OkHttpClient()


class PlaceOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)
        init()

        clicks()


        val Req = Request.Builder().url(SupplierEndPoint).build()

        ok.newCall(Req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("FEATCHEDSUPPS-ERROR",e.toString())
            }

        override fun onResponse(call: Call, response: Response) {
               try {
                   val JARRAY = JSONArray(response.body?.string())
                   Log.d("FEATCHEDSUPPS-SUCESSS",JARRAY.toString())
                   ShopsList = ArrayList()
                   for (i in 0 until JARRAY.length())
                   {
                       val JSO = JARRAY.getJSONObject(i)
                       val JSOPlants = JARRAY.getJSONArray(i)
                       val PlantList = ArrayList<Plants>()
                       for (i in 0 until JSOPlants.length())
                       {
                           val Plant = JSOPlants.getJSONObject(i);
                           val p = Plants(
                               Plant.getString("Plantname"),
                               Plant.getString("Plantimgurl"),
                               Plant.get("price").toString().toDouble(),
                               Plant.getString("about"),
                                Plant.getString("mode"),
                                Plant.getString("varient"),
                                0)
                           PlantList.add(p)
                       }
                       val Shops = Shops(
                           JSO.getString("Suppliername"),
                           JSO.getString("ShopImageURl"),
                           JSO.getString("location"),
                           PlantList
                       )
                       ShopsList.add(Shops)

                   }

                   runOnUiThread( object : Runnable
                   {
                       override fun run() {
                           val ADAPTOR:ShopsAdaptor  = ShopsAdaptor(this@PlaceOrder, ShopsList);
                           rec_shoppers.layoutManager = LinearLayoutManager(this@PlaceOrder)
                           rec_shoppers.adapter = ADAPTOR
                       }

                   })
               }
               catch (E:Exception)
               {
                   Log.d("FEATCHEDSUPPS-Catch",E.toString())
               }
            }

        })







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


        ettext.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString()!=null)
                {

                }
                else
                {

                }
            }

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