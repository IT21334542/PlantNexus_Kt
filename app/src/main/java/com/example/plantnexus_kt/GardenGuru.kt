package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
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

class GardenGuru : AppCompatActivity() {
    private val client = OkHttpClient()
    lateinit var home : ImageView
    lateinit var txtResponse: TextView
    lateinit var idTVQuestion: TextView
    lateinit var etQuestion: TextInputEditText
    val history = ArrayList<String>()
    lateinit var mListView: ListView
    lateinit var arrayAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garden_guru)
        etQuestion = findViewById(R.id.etQuestion)
        idTVQuestion = findViewById(R.id.idTVQuestion)
        home = findViewById(R.id.home_serach)
        txtResponse = findViewById(R.id.txtResponse)
        mListView = findViewById(R.id.responselist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, history)
        mListView.adapter = arrayAdapter

        etQuestion.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                txtResponse.text = "Please wait.."
                val question = etQuestion.text.toString().trim()
                if (question.isNotEmpty()) {
                    history.add("User: $question")
                    arrayAdapter.notifyDataSetChanged()
                    txtResponse.text = "Please wait..."
                    getResponse(question) { response ->
                        runOnUiThread {
                            history.add("Garden Guru: $response")
                            arrayAdapter.notifyDataSetChanged()
                            txtResponse.text = response
                            etQuestion.setText("")
                        }
                    }
                }
                return@OnEditorActionListener true
            }
            false
        })

        home.setOnClickListener {
            startActivity(Intent(this@GardenGuru,Login::class.java))
        }

    }

    fun getResponse(question: String, callback: (String) -> Unit) {
        idTVQuestion.text = question
        etQuestion.setText("")

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
                var textResult = jsonArray.getJSONObject(0).getString("text")
                if (question.equals("Hi", ignoreCase = true) || question == "Hello") {
                    textResult = "Hi:) Welcome to Garden Guru! Your Personal Companion"
                } else if (question == "How are you") {
                    textResult = "I'm just an Assistant , so I don't have feelings, but thanks for asking! How can I assist you today?"
                } else if(question.equals("play music", ignoreCase = true) || question.equals("play video", ignoreCase = true)){
                    textResult = "Sorry You must Upgrade your membership for that"
                    runOnUiThread {
                        val alertDialog = AlertDialog.Builder(this@GardenGuru)
                            .setTitle("Premium Required")
                            .setMessage("Sorry You must Upgrade your membership to play Music / Video")
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }else if(question.equals("place order", ignoreCase = true) || question.equals("view order", ignoreCase = true)){
                    textResult = "Sorry You must Upgrade your membership for placing order Virtually"
                    runOnUiThread {
                        val alertDialog = AlertDialog.Builder(this@GardenGuru)
                            .setTitle("Premium Required")
                            .setMessage("Sorry You must Upgrade your membership for placing order Virtually")
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                } else if(question.equals("what can you do", ignoreCase = true) || question.equals("what else can you do", ignoreCase = true)){
                    textResult = "I can give you the details of the plants if you give me the name of the plant"
                }
                if (textResult.length > 300) {
                    textResult = "Upgrade Your Membership"
                    runOnUiThread {
                        val alertDialog = AlertDialog.Builder(this@GardenGuru)
                            .setTitle("Premium Required")
                            .setMessage("To View Long Responses you need to Upgrade your Membership")
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
                callback(textResult)
            }
        })
    }
}
