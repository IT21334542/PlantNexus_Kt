package com.example.plantnexus_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Registerseller : AppCompatActivity() {
    private lateinit var storename:EditText
    private lateinit var address:EditText
    private lateinit var fullname:EditText
    private lateinit var contactnumber:EditText
    private lateinit var business:Spinner
    private lateinit var next:Button
    private lateinit var back:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerseller)
        init()
        back.setOnClickListener { startActivity(Intent(this@Registerseller,Register_choose::class.java)) }
        next.setOnClickListener {
            if(fullname.text.toString()=="" || storename.text.toString()=="" || business.toString()=="" || contactnumber.text.toString()==""){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }
            else if(contactnumber.length()!=10) {
            runOnUiThread {
                val alertDialog = AlertDialog.Builder(this@Registerseller)
                    .setTitle("Check Contact Number")
                    .setMessage("Contact number must be 10 digits")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
            }else{
                startActivity(Intent(this@Registerseller,DashBoard_Customer::class.java))
                Toast.makeText(this,"Register Successful",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init(){
        storename = findViewById(R.id.storename)
        address = findViewById(R.id.address)
        fullname = findViewById(R.id.fullnameseller)
        contactnumber = findViewById(R.id.phonenumber)
        business = findViewById(R.id.business)
        next = findViewById(R.id.next)
        back = findViewById(R.id.back)
    }
}