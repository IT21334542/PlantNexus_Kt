package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.plantnexus_kt.Models.Plants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class SearchResults_nexus : AppCompatActivity() {
    private lateinit var home_serach          : ImageView
    private lateinit var navigateback_serach  : ImageView
    private lateinit var PlantImage  : ImageView
    private lateinit var aboutplant           : TextView
    private lateinit var txttiltle            : TextView
    private lateinit var txtname : TextView
    private lateinit var oriname : String
    private lateinit var oriquestion :String
    private lateinit var question : String
    private val client = OkHttpClient()
    private val clientname = OkHttpClient()

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

        question = "Give me the details about the " + txttiltle.text.toString() + "in 40 words"
        oriquestion="Give me the non scientific name of " + txttiltle.text.toString()

        getResponse(question) { response ->
            runOnUiThread {
                aboutplant.text = response
            }
        }
        getResponse(oriquestion) { response ->
            runOnUiThread {
                oriname = response
                txtname.text = response
                Log.v("Name of plant",oriname.toString())
            }
        }

    }

    private fun init(){
        home_serach = findViewById(R.id.home_or)
        navigateback_serach = findViewById(R.id.navigateback_serach)
        PlantImage = findViewById(R.id.imgsP)
        aboutplant = findViewById(R.id.aboutplant)
        txttiltle = findViewById(R.id.txttiltle)
        txtname = findViewById(R.id.txtname)
    }


    private fun getResponse(question: String, callback: (String) -> Unit) {

        val apiKey = "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"
        val url = "https://api.openai.com/v1/engines/text-davinci-003/completions"

        val requestBody = """
            {
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "API failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    Log.v("data", body)
                } else {
                    Log.v("data", "empty")
                }
                val jsonObject = JSONObject(body)
                val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                val textResults = jsonArray.getJSONObject(0).getString("text")
                callback(textResults)
                Log.v("Result",textResults)
            }
        })
    }
}