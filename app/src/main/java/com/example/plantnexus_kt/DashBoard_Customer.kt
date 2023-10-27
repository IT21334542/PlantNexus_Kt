package com.example.plantnexus_kt

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantnexus_kt.Adapters.ProductAdaptor
import com.example.plantnexus_kt.Models.Plants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
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
    private lateinit var OpenTemp        : RelativeLayout
    private lateinit var TempCard        : RelativeLayout
    private lateinit var closeT        : RelativeLayout
    private lateinit var rec_products   : RecyclerView
    private lateinit var rec_products_grid  : RecyclerView
    private lateinit var ok : OkHttpClient
    private  var click = false;
    private lateinit var fetchurl : Request
    private lateinit var Temp :TextView
    private lateinit var phaseT :TextView
    private lateinit var LocationP : FusedLocationProviderClient

    val jsobj = JSONObject()
    val DATAFETCHED = null;
    val REQUEST_IMAGE_CAPTURE = 100
    val JSON = ("application/json; charset=utf-8").toMediaTypeOrNull()


    val PlantScanURL ="https://plant.id/api/v3/identification"
    var PlantsList = ArrayList<Plants>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_customer);

        init()

        val urlPlants = "https://us-east-1.aws.data.mongodb-api.com/app/procurementx1-msxsm/endpoint/Plants";
        val PlantsFetchRequest = Request.Builder().url(urlPlants).build();

        val axios = OkHttpClient()
        axios.newCall(PlantsFetchRequest).enqueue(object :Callback
        {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("PLANTFEATCH - ERROR :",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val Plants_list = ArrayList<Plants>()
                val RM = response.body?.string().to(ArrayList<Any>())
                val T= RM.first
                val g = JSONArray(T)
                for ( i in 0 until g.length())
                {
                    val plant = g.getJSONObject(i)
                    val p1 = Plants(
                        plant.get("Plantname").toString(),
                        plant.get("Plantimgurl").toString(),
                        plant.get("price").toString().toDouble(),
                        plant.get("about").toString(),
                        plant.get("mode").toString(),
                        plant.get("varient").toString(),
                        plant.get("qty").toString().toInt()
                        )
                    PlantsList.add(p1)
                }


            }

        })








        closeT.setOnClickListener(View.OnClickListener {
            OpenTemp.visibility = View.VISIBLE
            TempCard.visibility = View.INVISIBLE;
        })


        card_chatbot.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@DashBoard_Customer,GardenGuru::class.java))
        })





//scan plant
        card_scanplnat.setOnClickListener(View.OnClickListener {
//            val to : Intent = Intent(this@DashBoard_Customer,Scan::class.java)
//            startActivity(to)

            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            try {

                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
            }catch (e:Exception)
            {
                Toast.makeText(this@DashBoard_Customer,"Error: " + e.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })


        card_myplants.setOnClickListener(View.OnClickListener {
            val to : Intent = Intent(this@DashBoard_Customer,PlaceOrder::class.java)
           startActivity(to)
        })




        OpenTemp.setOnClickListener(View.OnClickListener {
            OpenTemp.visibility = View.INVISIBLE
            TempCard.visibility = View.VISIBLE;
        })
        val urI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXXSw2WdbA_N-adSzC8inRb41z191p-DVktreRD5W4xq4UoZIMSKE9KmwD7uCwfdsj4t4&usqp=CAU"

        val p1 = Plants("Cactus",urI,3300.00);
        val p2 = Plants("Cactus2",urI,3300.00);
        val p3 = Plants("Cactus2",urI,3300.00);
        val p4 = Plants("Cactus2",urI,3300.00);
        val p5 = Plants("Cactus2",urI,3300.00);




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
                    710
                )
                prams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)

                rec_products.visibility = View.VISIBLE
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

                rec_products.visibility = View.GONE
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){


            val imageBitmap = data?.extras?.get("data") as Bitmap
// Convert the image to a Base64 string
            val base64Image = bitmapToBase64(imageBitmap)



            if (base64Image!=null || base64Image != "")
            {

                jsobj.put("images",base64Image)

                val Body : RequestBody = makeBody(jsobj)
                val PlantRequest :Request =Request
                    .Builder()
                    .url(PlantScanURL)
                    .addHeader("Api-Key","DQkaUHA1PCvxWvoxt0D1n4DJAA5mpi0VaOLm7Py2vo3Rd8K3UO")
                    .post(Body)
                    .build()

                ok.newCall(PlantRequest).enqueue(object : Callback
                {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d("PLANTIDENTIFICATION -  ERROR",e.toString())
                    }

                    override fun onResponse(call: Call, response: Response) {
                        //Log.d("PLANTIDENTIFICATION -  SUCESS-64 ",base64Image)
                        //Log.d("PLANTIDENTIFICATION -  SUCESS ",response.body?.string().toString())
                        val res = JSONObject(response.body?.string())
                        val inputObject = res.getJSONObject("input")
                        val classifications = res.getJSONObject("result")
                        val customfromClassification = classifications.getJSONObject("classification")
                        val Suggestions = customfromClassification.getJSONArray("suggestions")
                        val mostProb = Suggestions.getJSONObject(0)
                        val PlantName = mostProb.getString("name")
                        val image = inputObject.getJSONArray("images")
                        val Url = image.getString(0)

                        val p1 = Plants(
                            PlantName,
                            Url,
                            0.00,
                            ""
                        )

                        val to = Intent(this@DashBoard_Customer,SearchResults_nexus::class.java)
                        to.putExtra("Plant",p1)
                        startActivity(to)
                        //val CapturedImage = inputObject.getJSONArray("images")
                       // Log.d("PLANTIDENTIFICATION -  SUCESS ",Url.toString())
                    }

                })





            }




        }else
            super.onActivityResult(requestCode, resultCode, data)

    }

    private fun makeBody(a: JSONObject): RequestBody
    {
        val body = RequestBody.create(JSON,a.toString())
        return body

    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


    private fun init(){
        card_scanplnat = findViewById(R.id.card_scanplnat)
        card_myplants = findViewById(R.id.card_myplants)
        card_chatbot = findViewById(R.id.card_chatbot)
        card_orders = findViewById(R.id.card_orders)
        blogone = findViewById(R.id.blogone)
        blogtwo = findViewById(R.id.blogtwo)
        blogthree = findViewById(R.id.blogthree)
        OpenTemp = findViewById(R.id.Tempholder_dash_minMized)
        TempCard = findViewById(R.id.Tempholder_dash)
        dragger = findViewById(R.id.dragger)
        holderr = findViewById(R.id.rec_holder_dash)
        rec_products = findViewById(R.id.products_dash)
        closeT = findViewById(R.id.close_temp_details)
        ok = OkHttpClient()


        rec_products_grid = findViewById(R.id.products_dash_grid)
        phaseT =findViewById(R.id.tempPhase)
        LocationP = LocationServices.getFusedLocationProviderClient(this@DashBoard_Customer)
        Temp = findViewById(R.id.tempValue)
        getLocation()
    }

    private fun getLocation() {

        if ((ActivityCompat.checkSelfPermission(
                this@DashBoard_Customer,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
            (ActivityCompat.checkSelfPermission(
                this@DashBoard_Customer,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
           ){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                100)
            return
        }


        val loca =  LocationP.lastLocation
        loca.addOnSuccessListener {
            if (it!=null)
            {
                val Lati = it.latitude
                val logi = it.longitude
                val url = "https://atlas.microsoft.com/weather/currentConditions/json?api-version=1.0&query="+Lati+","+logi+"&subscription-key=Je5NT-QjhXQy0qCVwklWqvRNir1R1GkOHXnGpM9_V3A"
                val request = Request.Builder().url(url).build()

                ok.newCall(request).enqueue(object : Callback
                {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d("TEMPFEATCH-FAIL",e.toString())
                    }

                    override fun onResponse(call: Call, response: Response) {

                        val Jobject = JSONObject(response.body?.string());

                        val JResults :JSONArray = Jobject.get("results") as JSONArray

                        val JCommit = JResults.get(0) as JSONObject

                        val phase = JCommit.getString("phrase")
                        val Tempuratur = JCommit.get("temperature") as JSONObject
                        val Value = Tempuratur.getString("value")
                        //val name :String = phase +"\n"+Value
                        Temp.text = Value
                        phaseT.text = phase
                        Log.d("TEMPFEATCH-SUCESS",phase+Value)
                    }

                })

            }
        }

    }


}