package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class TempActivity : AppCompatActivity() {

    private lateinit var backN :ImageView
    private lateinit var HomeN :ImageView
    private lateinit var weatherIcon :ImageView
    private lateinit var location :TextView
    private lateinit var phasetxt :TextView
    private lateinit var calcuis :TextView
    private lateinit var gide :TextView
    private lateinit var gidefeed :TextView
    private lateinit var huum :TextView
    private lateinit var deew :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)
        initall()

        onClicks()
        val ok = OkHttpClient()
        val LAT = intent.getDoubleExtra("Lati", 6.8748707)
        val LOGI = intent.getDoubleExtra("Longi", 79.8625086)
        val URl2 = "https://atlas.microsoft.com/search/address/reverse/json?subscription-key=Je5NT-QjhXQy0qCVwklWqvRNir1R1GkOHXnGpM9_V3A&api-version=1.0&query="+LAT+","+LOGI
        val R2 = Request.Builder().url(URl2).build()
        ok.newCall(R2).enqueue(object : Callback
        {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("LOCOL - error",e.toString())
            }

            override fun onResponse(call: Call, response: Response)
            {
                try {
                    val Jobject = JSONObject(response.body?.string());

                    val AddressArray = Jobject.getJSONArray("addresses");
                    Log.d("LOCOL - SucessA",AddressArray.toString())
                    val Eone = AddressArray.getJSONObject(0);
                    val Ein = Eone.getJSONObject("address")
                    Log.d("LOCOL - Sucess",Ein.toString())
                    val MUNI = Ein.getString("municipality")
                    val COUNTR = Ein.getString("country")

                    runOnUiThread(object : Runnable
                    {
                        override fun run() {
                            val h = MUNI+", "+COUNTR
                            location.text =h
                        }

                    })

                }catch (E:Exception)
                {
                    Log.d("LOCAO",E.toString())
                }

            }

        })
//        val LAT = 6.8748707
//        val LOGI = 79.8625086


        val A = LAT.toString()+" , "+LOGI.toString()

        val  url = "https://atlas.microsoft.com/weather/currentConditions/json?api-version=1.0&query="+LAT+","+LOGI+"&subscription-key=Je5NT-QjhXQy0qCVwklWqvRNir1R1GkOHXnGpM9_V3A"
        val request = Request.Builder().url(url).build()

        ok.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("TempCall - error",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try{
                    val Jobject = JSONObject(response.body?.string());

                    Log.d("TPTM - sucess",Jobject.toString())

                    val JResults: JSONArray = Jobject.get("results") as JSONArray

                    val JCommit = JResults.get(0) as JSONObject

                    val phase = JCommit.getString("phrase")
                    val Humidi = JCommit.getString("relativeHumidity")
                    val DewObject = JCommit.getJSONObject("dewPoint")
                    val isPrecip = JCommit.getBoolean("hasPrecipitation")
                    val cloudcound = JCommit.get("cloudCover")
                    val DewVlaue = DewObject.get("value")
                    val Tempuratur = JCommit.get("temperature") as JSONObject
                    val Value = Tempuratur.getString("value")
                    println("need"+Humidi+DewVlaue.toString())

                    runOnUiThread(object : Runnable
                    {
                        override fun run() {
                            phasetxt.text = phase
                            calcuis.text = Value
                            huum.text = Humidi
                            deew.text = DewVlaue.toString()
                            if (isPrecip)
                            {
                                Glide.with(this@TempActivity).asBitmap().load(R.mipmap.rainny_foreground).into(weatherIcon)
                                gide.text = "Watering recommendation : VERY LOW"
                                gidefeed.text = "Plant Feeding/ Fertilizing : Risk"
                            }else
                            {
                                println(cloudcound.toString().toInt())
                                if(cloudcound.toString().toInt()==0)
                                {
                                    Glide.with(this@TempActivity).asBitmap().load(R.mipmap.sunny).into(weatherIcon)
                                    gide.text = "Watering recommendation : HIGH"
                                    gidefeed.text = "Plant Feeding/ Fertilizing : MUST"
                                }
                                else if (cloudcound.toString().toInt()<=50)
                                {
                                    Glide.with(this@TempActivity).asBitmap().load(R.mipmap.mid_foreground).into(weatherIcon)
                                    gide.text = "Watering recommendation : LOW"
                                    gidefeed.text = "Plant Feeding/ Fertilizing : NEEDED"
                                }
                                else
                                {
                                    Glide.with(this@TempActivity).asBitmap().load(R.mipmap.cloudy_foreground).into(weatherIcon)
                                    gide.text = "Watering recommendation : LOW"
                                    gidefeed.text = "Plant Feeding/ Fertilizing : NORMAL"
                                }
                            }
                        }

                    })


                   // Dew.text = DewVlaue
            }catch (E:Exception)
                {
                Log.d("TPTM- Error",E.message.toString())

                }
            }

        })

    }

    private fun onClicks() {
        backN.setOnClickListener(View.OnClickListener {
            try{
                startActivity(Intent(this@TempActivity,DashBoard_Customer::class.java))
            }catch (E:Exception)
            {
                Log.d("backHomeError",E.toString())
            }
        })

        HomeN.setOnClickListener(View.OnClickListener {
            try{
                startActivity(Intent(this@TempActivity,DashBoard_Customer::class.java))
            }catch (E:Exception)
            {
                Log.d("backHomeError",E.toString())
            }
        })
    }

    private fun initall() {
        backN = findViewById(R.id.back_or)
        HomeN = findViewById(R.id.home_or)
        weatherIcon = findViewById(R.id.tempPhases)
        location = findViewById(R.id.location)
        phasetxt = findViewById(R.id.mini)
        calcuis = findViewById(R.id.CelciusValue)
        gide= findViewById(R.id.guide)
        gidefeed= findViewById(R.id.guidetwo)
        huum= findViewById(R.id.HumidityV)
        deew= findViewById(R.id.DewPointV)
    }
}